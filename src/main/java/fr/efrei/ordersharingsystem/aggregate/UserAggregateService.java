package fr.efrei.ordersharingsystem.aggregate;

import fr.efrei.ordersharingsystem.commands.users.CreateUserCommand;
import fr.efrei.ordersharingsystem.commands.users.DeleteUserCommand;
import fr.efrei.ordersharingsystem.commands.users.ModifyUserCommand;
import fr.efrei.ordersharingsystem.domain.User;

public interface UserAggregateService {
    User handle(CreateUserCommand command);
    void handle(ModifyUserCommand command);
    void handle(DeleteUserCommand command);
}
