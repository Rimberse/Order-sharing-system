package fr.efrei.ordersharingsystem.commands.orders;

import fr.efrei.ordersharingsystem.domain.Status;
import jakarta.validation.constraints.NotBlank;

public record ModifyOrderCommand(
        Long id,
        Long userId,
        Long parkId,
        Integer alleyNumber,
        @NotBlank Status status
) {
}
