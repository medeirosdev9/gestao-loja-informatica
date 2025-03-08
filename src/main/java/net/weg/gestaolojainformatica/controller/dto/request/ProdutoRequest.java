package net.weg.gestaolojainformatica.controller.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import net.weg.gestaolojainformatica.model.Produto;

import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank String nome,
        @NotNull @DecimalMin("0.01") BigDecimal preco,
        @NotNull @Min(0) Integer estoque,
        String descricao,
        @NotNull Long fornecedorId
) {
    public Produto toEntity() {
        return Produto.builder()
                .nome(this.nome)
                .preco(this.preco)
                .estoque(this.estoque)
                .descricao(this.descricao)
                .build();
    }
}
