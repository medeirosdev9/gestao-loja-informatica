package net.weg.gestaolojainformatica.service;

import lombok.RequiredArgsConstructor;
import net.weg.gestaolojainformatica.model.*;
import net.weg.gestaolojainformatica.repository.*;
import net.weg.gestaolojainformatica.controller.dto.request.ItemPedidoRequest;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public ItemPedido create(ItemPedidoRequest itemPedidoRequest) {
        Pedido pedido = pedidoRepository.findById(itemPedidoRequest.pedidoId())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        Produto produto = produtoRepository.findById(itemPedidoRequest.produtoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (itemPedidoRequest.precoUnitario().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço unitário deve ser maior que zero.");
        }

        if (itemPedidoRequest.quantidade() < 1) {
            throw new IllegalArgumentException("A quantidade deve ser no mínimo 1.");
        }

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(itemPedidoRequest.quantidade());
        itemPedido.setPrecoUnitario(itemPedidoRequest.precoUnitario());

        return itemPedidoRepository.save(itemPedido);
    }


    public ItemPedido update(Long id, ItemPedidoRequest itemPedidoRequest) {
        ItemPedido existing = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemPedido não encontrado"));

        Pedido pedido = pedidoRepository.findById(itemPedidoRequest.pedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Produto produto = produtoRepository.findById(itemPedidoRequest.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (itemPedidoRequest.precoUnitario().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O preço unitário deve ser maior que zero.");
        }

        if (itemPedidoRequest.quantidade() < 1) {
            throw new RuntimeException("A quantidade deve ser no mínimo 1.");
        }

        existing.setPedido(pedido);
        existing.setProduto(produto);
        existing.setQuantidade(itemPedidoRequest.quantidade());
        existing.setPrecoUnitario(itemPedidoRequest.precoUnitario());

        return itemPedidoRepository.save(existing);
    }

    public void delete(Long id) {
        itemPedidoRepository.deleteById(id);
    }

    public Optional<ItemPedido> getById(Long id) {
        return itemPedidoRepository.findById(id);
    }
}
