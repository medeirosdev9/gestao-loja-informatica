package net.weg.gestaolojainformatica.controller.dto.response;

import net.weg.gestaolojainformatica.model.Cliente;

public record ClienteResponse(
        Long id,
        String nome,
        String email,
        String telefone,
        String endereco
) {
    public static ClienteResponse toEntity(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getEndereco()
        );
    }
}
