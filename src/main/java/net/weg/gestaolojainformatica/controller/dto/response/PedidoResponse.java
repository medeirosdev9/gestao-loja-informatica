package net.weg.gestaolojainformatica.controller.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PedidoResponse(
        Long id,
        Long clienteId,
        BigDecimal total,
        LocalDate dataPedido
) {}
