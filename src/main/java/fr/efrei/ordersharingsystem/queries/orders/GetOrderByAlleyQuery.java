package fr.efrei.ordersharingsystem.queries.orders;

import fr.efrei.ordersharingsystem.domain.Status;
import jakarta.validation.constraints.NotBlank;

public record GetOrderByAlleyQuery(
        @NotBlank Long parkId,
        @NotBlank Integer alleyNumber
        ) {}
