package fr.efrei.ordersharingsystem.dataaccess.queries.users;

import fr.efrei.ordersharingsystem.entity.Role;
import jakarta.validation.constraints.NotBlank;

public record GetUserByAssignedBowlingParkIdQuery(
        Role role,
        @NotBlank Long assignedBowlingParkId
) {
}
