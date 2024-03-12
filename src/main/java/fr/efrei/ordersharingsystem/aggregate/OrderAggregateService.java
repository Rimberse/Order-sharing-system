package fr.efrei.ordersharingsystem.aggregate;

import fr.efrei.ordersharingsystem.commands.orders.*;

public interface OrderAggregateService {
    void handle(SetOrderItemCommand command);
    void handle(DeleteOrderCommand command);
    void handle(DeleteOrderItemCommand command);
}
