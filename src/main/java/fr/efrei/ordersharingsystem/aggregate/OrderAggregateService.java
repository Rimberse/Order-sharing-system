package fr.efrei.ordersharingsystem.aggregate;

import fr.efrei.ordersharingsystem.commands.orders.AddOrderCommand;
import fr.efrei.ordersharingsystem.commands.orders.ModifyOrderCommand;
import fr.efrei.ordersharingsystem.commands.orders.ModifyOrderItemCommand;

public interface OrderAggregateService {
    long handle(AddOrderCommand command);
    void handle(ModifyOrderCommand command);
    void handle(ModifyOrderItemCommand command);
}
