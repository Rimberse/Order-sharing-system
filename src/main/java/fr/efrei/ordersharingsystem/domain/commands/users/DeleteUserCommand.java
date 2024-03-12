package fr.efrei.ordersharingsystem.domain.commands.users;

import jakarta.validation.constraints.NotBlank;

public record DeleteUserCommand(@NotBlank long id) {
}
