package fr.efrei.ordersharingsystem.commands.sessions;

import jakarta.validation.constraints.NotBlank;

public record DeleteSessionCommand(
        @NotBlank Long id,
        @NotBlank Long parkId,
        @NotBlank Integer alleyNumber
) {
}
