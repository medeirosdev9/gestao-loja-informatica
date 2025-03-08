package net.weg.gestaolojainformatica.controller.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ItemPedidoRequest(
        @NotNull(message = "O pedido ID não pode ser nulo") Long pedidoId,
        @NotNull(message = "O produto ID não pode ser nulo") Long produtoId,
        @NotNull(message = "A quantidade não pode ser nula") @Min(value = 1, message = "A quantidade deve ser no mínimo 1") Integer quantidade,
        @NotNull(message = "O preço unitário não pode ser nulo") @DecimalMin(value = "0.01", message = "O preço unitário deve ser maior que zero") BigDecimal precoUnitario
) {}
