package fr.efrei.ordersharingsystem.commands.orders;

import jakarta.validation.constraints.NotBlank;

public record ModifyOrderItemCommand(
        Long id,
        Long userId,
        Long parkId,
        Integer alleyNumber,
        Long orderId,
        Long productId,
        @NotBlank Integer quantity
) {
}
