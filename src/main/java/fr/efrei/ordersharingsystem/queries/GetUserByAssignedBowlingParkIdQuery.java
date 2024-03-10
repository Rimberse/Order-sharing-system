package fr.efrei.ordersharingsystem.queries;

import fr.efrei.ordersharingsystem.domain.BowlingPark;
import fr.efrei.ordersharingsystem.domain.Role;
import jakarta.validation.constraints.NotBlank;

public record GetUserByAssignedBowlingParkIdQuery(
        Role role,
        @NotBlank BowlingPark assignedBowlingParkId
) {
}
