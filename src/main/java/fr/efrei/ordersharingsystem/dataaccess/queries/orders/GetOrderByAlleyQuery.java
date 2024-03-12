package fr.efrei.ordersharingsystem.dataaccess.queries.orders;

import jakarta.validation.constraints.NotBlank;

public record GetOrderByAlleyQuery(
        @NotBlank Long parkId,
        @NotBlank Integer alleyNumber
        ) {}
