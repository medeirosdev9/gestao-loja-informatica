package net.weg.gestaolojainformatica.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.weg.gestaolojainformatica.controller.dto.request.PedidoRequest;
import net.weg.gestaolojainformatica.controller.dto.response.PedidoResponse;
import net.weg.gestaolojainformatica.model.Pedido;
import net.weg.gestaolojainformatica.service.PedidoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponse> create(@RequestBody @Valid PedidoRequest pedidoRequest) {
        try {
            Pedido pedido = pedidoService.create(pedidoRequest);
            PedidoResponse pedidoResponse = new PedidoResponse(
                    pedido.getId(),
                    pedido.getCliente().getId(),
                    pedido.getTotal(),
                    pedido.getDataPedido()
            );
            return new ResponseEntity<>(pedidoResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> update(@RequestBody @Valid PedidoRequest pedidoRequest, @PathVariable Long id) {
        try {
            Pedido pedidoAtualizado = pedidoService.update(id, pedidoRequest);
            PedidoResponse pedidoResponse = new PedidoResponse(
                    pedidoAtualizado.getId(),
                    pedidoAtualizado.getCliente().getId(),
                    pedidoAtualizado.getTotal(),
                    pedidoAtualizado.getDataPedido()
            );
            return new ResponseEntity<>(pedidoResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        try {
            pedidoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> findById(@PathVariable Long id) {
        try {
            Pedido pedido = pedidoService.getById(id);
            PedidoResponse pedidoResponse = new PedidoResponse(
                    pedido.getId(),
                    pedido.getCliente().getId(),
                    pedido.getTotal(),
                    pedido.getDataPedido()
            );
            return new ResponseEntity<>(pedidoResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<PedidoResponse>> findAll(Pageable pageable) {
        Page<Pedido> pedidos = pedidoService.getAll(pageable);
        if (pedidos.hasContent()) {
            Page<PedidoResponse> pedidoResponses = pedidos.map(pedido -> new PedidoResponse(
                    pedido.getId(),
                    pedido.getCliente().getId(),
                    pedido.getTotal(),
                    pedido.getDataPedido()
            ));
            return new ResponseEntity<>(pedidoResponses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
