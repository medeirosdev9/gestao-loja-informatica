package net.weg.gestaolojainformatica.service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.weg.gestaolojainformatica.model.*;
import net.weg.gestaolojainformatica.repository.*;
import net.weg.gestaolojainformatica.controller.dto.request.*;
import net.weg.gestaolojainformatica.controller.dto.response.*;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final FornecedorRepository fornecedorRepository;

    public Page<ProdutoResponse> getAll(Pageable pageable) {
        return produtoRepository.findAll(pageable).map(ProdutoResponse::fromEntity);
    }

    public ProdutoResponse getById(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return ProdutoResponse.fromEntity(produto);
    }

    public ProdutoResponse create(ProdutoRequest request) {
        Fornecedor fornecedor = fornecedorRepository.findById(request.fornecedorId()).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        Produto produto = Produto.builder()
                .nome(request.nome())
                .preco(request.preco())
                .estoque(request.estoque())
                .descricao(request.descricao())
                .fornecedor(fornecedor)
                .build();
        produto = produtoRepository.save(produto);
        return ProdutoResponse.fromEntity(produto);
    }

    public ProdutoResponse update(Long id, ProdutoRequest request) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setNome(request.nome());
        produto.setPreco(request.preco());
        produto.setEstoque(request.estoque());
        produto.setDescricao(request.descricao());
        produtoRepository.save(produto);
        return ProdutoResponse.fromEntity(produto);
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }
}

