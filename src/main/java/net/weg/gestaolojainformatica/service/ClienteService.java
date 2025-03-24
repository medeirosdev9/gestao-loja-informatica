package net.weg.gestaolojainformatica.service;

import lombok.RequiredArgsConstructor;
import net.weg.gestaolojainformatica.repository.specification.ClienteSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import net.weg.gestaolojainformatica.model.*;
import net.weg.gestaolojainformatica.repository.*;
import net.weg.gestaolojainformatica.controller.dto.request.*;
import net.weg.gestaolojainformatica.controller.dto.response.*;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private Specification<Cliente> where = Specification.where(null);

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

    public ClienteResponse getById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return ClienteResponse.toEntity(cliente);
    }

    public Page<ClienteResponse> getByEndereco(String endereco, Pageable pageable) {
        Specification<Cliente> spec = ClienteSpecification.hasEndereco(endereco);
        return clienteRepository.findAll(spec, pageable).map(ClienteResponse::toEntity);
    }

    public Page<ClienteResponse> getByIdade(Integer idadeMin, Integer idadeMax, Pageable pageable) {
        Specification<Cliente> spec = ClienteSpecification.hasIdade(idadeMin, idadeMax);
        return clienteRepository.findAll(spec, pageable).map(ClienteResponse::toEntity);
    }

    public Page<ClienteResponse> getAll(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(ClienteResponse::toEntity);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    public Page<ClienteResponse> getByFiltros(String endereco, Integer idadeMin, Integer idadeMax, Pageable pageable) {
        Specification<Cliente> filtros = where;

        if (endereco != null && !endereco.isEmpty()) {
            filtros = filtros.and(ClienteSpecification.hasEndereco(endereco));
        }
        if (idadeMin != null && idadeMax != null) {
            filtros = filtros.and(ClienteSpecification.hasIdade(idadeMin, idadeMax));
        }

        return clienteRepository.findAll(filtros, pageable).map(ClienteResponse::toEntity);
    }
}