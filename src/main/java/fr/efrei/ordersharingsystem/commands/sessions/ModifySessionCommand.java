package fr.efrei.ordersharingsystem.commands.sessions;

import fr.efrei.ordersharingsystem.domain.Status;
import jakarta.validation.constraints.NotBlank;

public record ModifySessionCommand(
        Long id,
        Long parkId,
        Integer alleyNumber,
        @NotBlank Status status
) {
}
