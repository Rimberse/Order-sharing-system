package fr.efrei.ordersharingsystem.domain.aggregators;

import fr.efrei.ordersharingsystem.commands.orders.*;
import fr.efrei.ordersharingsystem.domain.commands.orders.DeleteOrderCommand;
import fr.efrei.ordersharingsystem.domain.commands.orders.DeleteOrderItemCommand;
import fr.efrei.ordersharingsystem.domain.commands.orders.SetOrderItemCommand;

public interface OrderAggregateService {
    void handle(SetOrderItemCommand command);
    void handle(DeleteOrderCommand command);
    void handle(DeleteOrderItemCommand command);
}
