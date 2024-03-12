package fr.efrei.ordersharingsystem.queries.orders;

import jakarta.validation.constraints.NotBlank;

public record GetRemainingAmountByOrderIdQuery(
        @NotBlank Long parkId,
        @NotBlank Integer alleyNumber,
        @NotBlank Long orderId
) {
}
