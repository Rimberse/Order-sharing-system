package fr.efrei.ordersharingsystem.dataaccess.queries.users;

import jakarta.validation.constraints.NotBlank;

public record GetUserByCredentialsQuery(
        String name,
        String email,
        @NotBlank String password
) {
}
