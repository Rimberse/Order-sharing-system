package fr.efrei.ordersharingsystem.domain.aggregators;

import fr.efrei.ordersharingsystem.domain.commands.users.CreateUserCommand;
import fr.efrei.ordersharingsystem.domain.commands.users.DeleteUserCommand;
import fr.efrei.ordersharingsystem.domain.commands.users.ModifyUserCommand;
import fr.efrei.ordersharingsystem.entity.User;

public interface UserAggregateService {
    User handle(CreateUserCommand command);
    void handle(ModifyUserCommand command);
    void handle(DeleteUserCommand command);
}
