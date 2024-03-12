package fr.efrei.ordersharingsystem.domain.commands.payments;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreatePaymentCommand(
        @NotBlank Long userId,
        Long orderId,
        @NotBlank @Min(value = 1, message = "Minimal amount is 1") Integer amount,
        @NotBlank String paymentAccount
) {
}
