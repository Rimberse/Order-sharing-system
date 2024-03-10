package fr.efrei.ordersharingsystem.commands;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModifyUserCommand(
        @NotBlank long id,
        @NotBlank @Size(min = 1, max = 50) String name,
        @NotBlank @Size(min = 5, max = 100) String email,
        @NotBlank @Size(min = 6, max = 255) String password,
        @Size(min = 10, max = 20) String phoneNumber,
        @NotBlank @Min(value = 1, message = "Minimal parkId is 1") int assignedBowlingParkId
) {
}
