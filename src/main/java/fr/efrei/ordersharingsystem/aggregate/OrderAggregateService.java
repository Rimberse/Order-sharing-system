package fr.efrei.ordersharingsystem.aggregate;

import fr.efrei.ordersharingsystem.commands.orders.*;

public interface OrderAggregateService {
    long handle(AddOrderCommand command);
    void handle(ModifyOrderItemCommand command);
    void handle(DeleteOrderItemCommand command);
}
