package fr.efrei.ordersharingsystem.domain.commands.orders;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


public record SetOrderItemCommand(
        Long userId,
        Long parkId,
        Integer alleyNumber,
        @NotBlank Long productId,
        @NotBlank @Min(value = 1, message = "Minimal quantity is 1") Integer quantity) {
}
