package net.weg.gestaolojainformatica.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.weg.gestaolojainformatica.controller.dto.request.ProdutoRequest;
import net.weg.gestaolojainformatica.controller.dto.response.ProdutoResponse;
import net.weg.gestaolojainformatica.service.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/produtos")
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponse> create(@RequestBody @Valid ProdutoRequest request) {
        try {
            ProdutoResponse produtoResponse = produtoService.create(request);
            return new ResponseEntity<>(produtoResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> update(@RequestBody @Valid ProdutoRequest request, @PathVariable Long id) {
        try {
            ProdutoResponse produtoResponse = produtoService.update(id, request);
            return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        try {
            produtoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> findById(@PathVariable Long id) {
        try {
            ProdutoResponse produtoResponse = produtoService.getById(id);
            return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoResponse>> findAll(Pageable pageable) {
        Page<ProdutoResponse> produtos = produtoService.getAll(pageable);
        if (produtos.hasContent()) {
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
