package fr.efrei.ordersharingsystem.commands.orders;

import fr.efrei.ordersharingsystem.domain.Status;
import jakarta.validation.constraints.NotBlank;

public record ModifyOrderCommand(
        @NotBlank Long id,
        @NotBlank Long userId,
        @NotBlank Long parkId,
        @NotBlank Integer alleyNumber,
        @NotBlank Status status
) {
}
