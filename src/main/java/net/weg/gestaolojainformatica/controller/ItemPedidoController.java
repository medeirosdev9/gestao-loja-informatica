package net.weg.gestaolojainformatica.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.weg.gestaolojainformatica.controller.dto.request.ItemPedidoRequest;
import net.weg.gestaolojainformatica.controller.dto.response.ItemPedidoResponse;
import net.weg.gestaolojainformatica.model.ItemPedido;
import net.weg.gestaolojainformatica.service.ItemPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itens-pedido")
@AllArgsConstructor
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    @PostMapping
    public ResponseEntity<ItemPedidoResponse> create(@RequestBody @Valid ItemPedidoRequest itemPedidoRequest) {
        try {
            ItemPedido savedItemPedido = itemPedidoService.create(itemPedidoRequest);
            ItemPedidoResponse response = new ItemPedidoResponse(
                    savedItemPedido.getId(),
                    savedItemPedido.getPedido().getId(),
                    savedItemPedido.getProduto().getId(),
                    savedItemPedido.getQuantidade(),
                    savedItemPedido.getPrecoUnitario()
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoResponse> update(@RequestBody @Valid ItemPedidoRequest itemPedidoRequest, @PathVariable Long id) {
        try {
            ItemPedido updatedItemPedido = itemPedidoService.update(id, itemPedidoRequest);
            ItemPedidoResponse response = new ItemPedidoResponse(
                    updatedItemPedido.getId(),
                    updatedItemPedido.getPedido().getId(),
                    updatedItemPedido.getProduto().getId(),
                    updatedItemPedido.getQuantidade(),
                    updatedItemPedido.getPrecoUnitario()
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


