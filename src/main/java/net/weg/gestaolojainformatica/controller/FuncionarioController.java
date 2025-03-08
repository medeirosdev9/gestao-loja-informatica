package net.weg.gestaolojainformatica.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.weg.gestaolojainformatica.model.Funcionario;
import net.weg.gestaolojainformatica.service.FuncionarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionarios")
@AllArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<Funcionario> create(@RequestBody @Valid Funcionario funcionario) {
        try {
            Funcionario savedFuncionario = funcionarioService.create(funcionario);
            return new ResponseEntity<>(savedFuncionario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@RequestBody @Valid Funcionario funcionario, @PathVariable Long id) {
        try {
            Funcionario updatedFuncionario = funcionarioService.update(id, funcionario);
            return new ResponseEntity<>(updatedFuncionario, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        try {
            funcionarioService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
        try {
            Funcionario funcionario = funcionarioService.getById(id)
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
            return new ResponseEntity<>(funcionario, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Funcionario>> findAll(Pageable pageable) {
        Page<Funcionario> funcionarios = funcionarioService.getAll(pageable);
        if (funcionarios.hasContent()) {
            return new ResponseEntity<>(funcionarios, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
