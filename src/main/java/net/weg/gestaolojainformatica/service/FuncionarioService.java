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
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public Page<Funcionario> getAll(Pageable pageable) {
        return funcionarioRepository.findAll(pageable);
    }

    public Optional<Funcionario> getById(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario create(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario update(Long id, Funcionario funcionario) {
        Funcionario existing = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        existing.setNome(funcionario.getNome());
        existing.setCargo(funcionario.getCargo());
        existing.setSalario(funcionario.getSalario());
        return funcionarioRepository.save(existing);
    }

    public void delete(Long id) {
        funcionarioRepository.deleteById(id);
    }
}
