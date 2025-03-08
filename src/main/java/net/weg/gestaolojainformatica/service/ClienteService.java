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
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public Page<ClienteResponse> getAll(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(ClienteResponse::toEntity);
    }

    public ClienteResponse getById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return ClienteResponse.toEntity(cliente);
    }

    public ClienteResponse create(ClienteRequest request) {
        Cliente cliente = clienteRepository.save(request.toEntity());
        return ClienteResponse.toEntity(cliente);
    }

    public ClienteResponse update(Long id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setNome(request.nome());
        cliente.setEmail(request.email());
        cliente.setTelefone(request.telefone());
        cliente.setEndereco(request.endereco());

        clienteRepository.save(cliente);
        return ClienteResponse.toEntity(cliente);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}