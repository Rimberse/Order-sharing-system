package fr.efrei.ordersharingsystem.commands.orders;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CreateOrderCommand(
        @NotBlank Long userId,
        @NotBlank Long parkId,
        @NotBlank Integer alleyNumber) {
}
