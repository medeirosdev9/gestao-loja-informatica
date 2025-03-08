package net.weg.gestaolojainformatica.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.weg.gestaolojainformatica.controller.dto.request.ClienteRequest;
import net.weg.gestaolojainformatica.controller.dto.response.ClienteResponse;
import net.weg.gestaolojainformatica.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> create(@RequestBody @Valid ClienteRequest clienteRequest) {
        try {
            ClienteResponse clienteResponse = clienteService.create(clienteRequest);
            return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> update(@RequestBody @Valid ClienteRequest clienteRequest, @PathVariable Long id) {
        try {
            ClienteResponse clienteResponse = clienteService.update(id, clienteRequest);
            return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        try {
            clienteService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> findById(@PathVariable Long id) {
        try {
            ClienteResponse clienteResponse = clienteService.getById(id);
            return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> findAll(Pageable pageable) {
        Page<ClienteResponse> clientes = clienteService.getAll(pageable);
        if (clientes.hasContent()) {
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
