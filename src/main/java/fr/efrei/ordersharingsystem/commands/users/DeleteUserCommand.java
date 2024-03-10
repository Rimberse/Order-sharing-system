package fr.efrei.ordersharingsystem.commands.users;

import jakarta.validation.constraints.NotBlank;

public record DeleteUserCommand(@NotBlank long id) {
}
