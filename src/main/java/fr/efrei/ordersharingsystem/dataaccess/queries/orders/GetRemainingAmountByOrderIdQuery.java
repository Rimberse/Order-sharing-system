package fr.efrei.ordersharingsystem.dataaccess.queries.orders;

import jakarta.validation.constraints.NotBlank;

public record GetRemainingAmountByOrderIdQuery(
        @NotBlank Long parkId,
        @NotBlank Integer alleyNumber,
        @NotBlank Long orderId
) {
}
