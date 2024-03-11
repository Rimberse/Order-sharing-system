package fr.efrei.ordersharingsystem.queries.orders;

import fr.efrei.ordersharingsystem.domain.Status;
import jakarta.validation.constraints.NotBlank;

public record GetOrdersByAlleyQuery(
        @NotBlank Long parkId,
        @NotBlank Integer alleyNumber,
        @NotBlank Status status
        ) {}
