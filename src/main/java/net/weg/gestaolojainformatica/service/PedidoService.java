package net.weg.gestaolojainformatica.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.weg.gestaolojainformatica.model.*;
import net.weg.gestaolojainformatica.repository.*;
import net.weg.gestaolojainformatica.controller.dto.request.PedidoRequest;
import net.weg.gestaolojainformatica.controller.dto.response.PedidoResponse;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository; // Se necessário para obter cliente pelo ID

    public Page<Pedido> getAll(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    public Pedido getById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public Pedido create(PedidoRequest pedidoRequest) {
        Cliente cliente = clienteRepository.findById(pedidoRequest.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setTotal(pedidoRequest.total());
        pedido.setDataPedido(pedidoRequest.dataPedido());

        return pedidoRepository.save(pedido);
    }

    public Pedido update(Long id, PedidoRequest pedidoRequest) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Cliente cliente = clienteRepository.findById(pedidoRequest.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        pedido.setCliente(cliente);
        pedido.setTotal(pedidoRequest.total());
        pedido.setDataPedido(pedidoRequest.dataPedido());

        return pedidoRepository.save(pedido);
    }


    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }
}
