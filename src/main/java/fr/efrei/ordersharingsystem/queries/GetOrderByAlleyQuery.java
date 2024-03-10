package fr.efrei.ordersharingsystem.queries;

import fr.efrei.ordersharingsystem.domain.Status;
import jakarta.validation.constraints.NotBlank;

public record GetOrderByAlleyQuery(
        @NotBlank Long parkId,
        @NotBlank Integer alleyNumber,
        @NotBlank Status status
        ) {}
