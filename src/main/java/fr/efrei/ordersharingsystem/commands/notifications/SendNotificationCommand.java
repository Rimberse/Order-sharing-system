package fr.efrei.ordersharingsystem.commands.notifications;

import fr.efrei.ordersharingsystem.domain.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record SendNotificationCommand(
        @NotBlank List<User> users,
        String message
) {
}
