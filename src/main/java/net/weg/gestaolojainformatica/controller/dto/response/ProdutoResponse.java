package net.weg.gestaolojainformatica.controller.dto.response;

import net.weg.gestaolojainformatica.model.Produto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        BigDecimal preco,
        Integer estoque,
        String descricao
) {
    public static ProdutoResponse fromEntity(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getEstoque(),
                produto.getDescricao()
        );
    }
}
