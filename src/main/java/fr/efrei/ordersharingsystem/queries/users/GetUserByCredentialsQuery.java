package fr.efrei.ordersharingsystem.queries.users;

import jakarta.validation.constraints.NotBlank;

public record GetUserByCredentialsQuery(
        String name,
        String email,
        @NotBlank String password
) {
}
