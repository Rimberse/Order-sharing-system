package fr.efrei.ordersharingsystem.commands.orders;

import jakarta.validation.constraints.NotBlank;

public record DeleteOrderCommand(
        @NotBlank Long id,
        @NotBlank Long parkId,
        @NotBlank Integer alleyNumber
) {
}
