package fr.efrei.ordersharingsystem.queries.sessions;

import fr.efrei.ordersharingsystem.domain.Status;
import jakarta.validation.constraints.NotBlank;

public record GetSessionByAlleyQuery(
        @NotBlank Long parkId,
        @NotBlank Integer alleyNumber,
        @NotBlank Status status
        ) {}
