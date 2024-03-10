package fr.efrei.ordersharingsystem.commands;

import jakarta.validation.constraints.NotBlank;

public record DeleteUserCommand(@NotBlank long id) {
}
