package fr.efrei.ordersharingsystem.projections;

import fr.efrei.ordersharingsystem.domain.User;
import fr.efrei.ordersharingsystem.queries.users.GetUserByAssignedBowlingParkIdQuery;
import fr.efrei.ordersharingsystem.queries.users.GetUserByCredentialsQuery;
import fr.efrei.ordersharingsystem.queries.users.GetUserByIdQuery;
import fr.efrei.ordersharingsystem.queries.users.GetUserByRole;

import java.util.List;

public interface UserProjectionService {
    List<User> handle();
    List<User> handle(GetUserByRole query);
    List<User> handle(GetUserByAssignedBowlingParkIdQuery query);
    User handle(GetUserByIdQuery query);
    User handle(GetUserByCredentialsQuery query);
}
