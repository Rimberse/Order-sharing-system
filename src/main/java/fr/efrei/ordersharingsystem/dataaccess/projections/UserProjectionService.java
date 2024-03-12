package fr.efrei.ordersharingsystem.dataaccess.projections;

import fr.efrei.ordersharingsystem.entity.User;
import fr.efrei.ordersharingsystem.dataaccess.queries.users.GetUserByAssignedBowlingParkIdQuery;
import fr.efrei.ordersharingsystem.dataaccess.queries.users.GetUserByCredentialsQuery;
import fr.efrei.ordersharingsystem.dataaccess.queries.users.GetUserByIdQuery;
import fr.efrei.ordersharingsystem.dataaccess.queries.users.GetUserByRole;

import java.util.List;

public interface UserProjectionService {
    List<User> handle();
    List<User> handle(GetUserByRole query);
    List<User> handle(GetUserByAssignedBowlingParkIdQuery query);
    User handle(GetUserByIdQuery query);
    User handle(GetUserByCredentialsQuery query);
}
