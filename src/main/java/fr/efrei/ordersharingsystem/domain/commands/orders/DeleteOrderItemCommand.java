package fr.efrei.ordersharingsystem.domain.commands.orders;

import jakarta.validation.constraints.NotBlank;

public record DeleteOrderItemCommand(
        @NotBlank Long id
) {
}
