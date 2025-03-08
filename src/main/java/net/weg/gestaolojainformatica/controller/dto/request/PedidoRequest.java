package net.weg.gestaolojainformatica.controller.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PedidoRequest(
        Long clienteId,
        BigDecimal total,
        LocalDate dataPedido
) {}
