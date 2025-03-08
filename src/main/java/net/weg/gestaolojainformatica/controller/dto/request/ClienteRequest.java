package net.weg.gestaolojainformatica.controller.dto.request;

import jakarta.validation.constraints.*;
import net.weg.gestaolojainformatica.model.Cliente;

public record ClienteRequest(
        @NotBlank String nome,
        @Email @NotBlank String email,
        @NotBlank String telefone,
        @NotBlank String endereco
) {
    public Cliente toEntity() {
        return Cliente.builder()
                .nome(this.nome)
                .email(this.email)
                .telefone(this.telefone)
                .endereco(this.endereco)
                .build();
    }
}
