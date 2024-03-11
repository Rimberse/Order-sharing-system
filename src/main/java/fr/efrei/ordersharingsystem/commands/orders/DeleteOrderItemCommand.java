package fr.efrei.ordersharingsystem.commands.orders;

import jakarta.validation.constraints.NotBlank;

public record DeleteOrderItemCommand(
        @NotBlank Long id
) {
}
