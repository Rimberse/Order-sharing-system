package fr.efrei.ordersharingsystem.domain.commands.products;

import jakarta.validation.constraints.NotBlank;

public record DeleteProductCommand(@NotBlank Long id) {
}
