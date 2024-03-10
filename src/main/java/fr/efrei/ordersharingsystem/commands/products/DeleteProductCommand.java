package fr.efrei.ordersharingsystem.commands.products;

import jakarta.validation.constraints.NotBlank;

public record DeleteProductCommand(@NotBlank Long id) {
}
