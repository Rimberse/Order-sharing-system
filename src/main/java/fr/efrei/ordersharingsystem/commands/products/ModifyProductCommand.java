package fr.efrei.ordersharingsystem.commands.products;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModifyProductCommand(
        @NotBlank long id,
        @NotBlank @Size(min = 1, max = 50) String name,
        @Size(min = 1, max = 255) String description,
        @NotBlank @Min(value = 1, message = "Minimal price is 1") int price) {
}
