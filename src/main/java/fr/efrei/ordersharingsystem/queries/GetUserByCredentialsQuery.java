package fr.efrei.ordersharingsystem.queries;

import jakarta.validation.constraints.NotBlank;

public record GetUserByCredentialsQuery(
        String name,
        String email,
        @NotBlank String password
) {
}
