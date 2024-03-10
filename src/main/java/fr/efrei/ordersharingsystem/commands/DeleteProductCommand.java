package fr.efrei.ordersharingsystem.commands;

import jakarta.validation.constraints.NotBlank;

public record DeleteProductCommand(@NotBlank long id) {
}
