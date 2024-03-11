package fr.efrei.ordersharingsystem.queries.users;

import fr.efrei.ordersharingsystem.domain.Role;
import jakarta.validation.constraints.NotBlank;

public record GetUserByAssignedBowlingParkIdQuery(
        Role role,
        @NotBlank Long assignedBowlingParkId
) {
}