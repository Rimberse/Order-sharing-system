package fr.efrei.ordersharingsystem.commands.payments;

import fr.efrei.ordersharingsystem.domain.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreatePaymentCommand(
        @NotBlank @Min(value = 1, message = "Minimal userId is 1") Long userId,
        @NotBlank @Min(value = 1, message = "Minimal orderId is 1") Long orderId,
        @NotBlank @Min(value = 1, message = "Minimal amount is 1") Integer amount,
        @NotBlank Status status
) {
}
