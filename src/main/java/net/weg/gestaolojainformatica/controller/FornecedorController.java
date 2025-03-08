package net.weg.gestaolojainformatica.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.weg.gestaolojainformatica.model.Fornecedor;
import net.weg.gestaolojainformatica.service.FornecedorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fornecedores")
@AllArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<Fornecedor> create(@RequestBody @Valid Fornecedor fornecedor) {
        try {
            Fornecedor savedFornecedor = fornecedorService.create(fornecedor);
            return new ResponseEntity<>(savedFornecedor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> update(@RequestBody @Valid Fornecedor fornecedor, @PathVariable Long id) {
        try {
            Fornecedor updatedFornecedor = fornecedorService.update(id, fornecedor);
            return new ResponseEntity<>(updatedFornecedor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        try {
            fornecedorService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> findById(@PathVariable Long id) {
        try {
            Fornecedor fornecedor = fornecedorService.getById(id)
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
            return new ResponseEntity<>(fornecedor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Fornecedor>> findAll(Pageable pageable) {
        Page<Fornecedor> fornecedores = fornecedorService.getAll(pageable);
        if (fornecedores.hasContent()) {
            return new ResponseEntity<>(fornecedores, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
