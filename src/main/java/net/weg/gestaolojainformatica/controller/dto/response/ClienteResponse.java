package net.weg.gestaolojainformatica.controller.dto.response;

import net.weg.gestaolojainformatica.model.Cliente;

public record ClienteResponse(
        Long id,
        String nome,
        String email,
        Integer idade,
        String telefone,
        String endereco
) {
    public static ClienteResponse toEntity(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getIdade(),
                cliente.getTelefone(),
                cliente.getEndereco()
        );
    }
}
