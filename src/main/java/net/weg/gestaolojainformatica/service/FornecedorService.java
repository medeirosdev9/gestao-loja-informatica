package net.weg.gestaolojainformatica.service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.weg.gestaolojainformatica.model.*;
import net.weg.gestaolojainformatica.repository.*;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;

    public Page<Fornecedor> getAll(Pageable pageable) {
        return fornecedorRepository.findAll(pageable);
    }

    public Optional<Fornecedor> getById(Long id) {
        return fornecedorRepository.findById(id);
    }

    public Fornecedor create(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor update(Long id, Fornecedor fornecedor) {
        Fornecedor existing = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        existing.setNome(fornecedor.getNome());
        existing.setContato(fornecedor.getContato());
        return fornecedorRepository.save(existing);
    }

    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    }
}
