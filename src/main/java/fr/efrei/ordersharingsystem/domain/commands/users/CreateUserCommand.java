package fr.efrei.ordersharingsystem.domain.commands.users;

import fr.efrei.ordersharingsystem.entity.Role;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserCommand(
        @Size(min = 1, max = 50) String firstName,
        @Size(min = 1, max = 50) String lastName,
        @NotBlank @Size(min = 1, max = 50) String name,
        @NotBlank @Size(min = 5, max = 100) String email,
        @NotBlank @Size(min = 6, max = 255) String password,
        @Size(min = 10, max = 20) String phoneNumber,
        @NotBlank Role role,
        @NotBlank @Min(value = 1, message = "Minimal parkId is 1") long assignedBowlingParkId
        ) {
}
