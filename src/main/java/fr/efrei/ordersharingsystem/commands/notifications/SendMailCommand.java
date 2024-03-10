package fr.efrei.ordersharingsystem.commands.notifications;

import fr.efrei.ordersharingsystem.domain.User;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record SendMailCommand(
        @NotBlank User user,
        String message,
        @NotBlank float invoice
) {
}
