package fr.efrei.ordersharingsystem.aggregate;

import fr.efrei.ordersharingsystem.commands.orders.CreateOrderCommand;
import fr.efrei.ordersharingsystem.commands.orders.ModifyOrderCommand;

public interface OrderAggregateService {
    long handle(CreateOrderCommand command);
    void handle(ModifyOrderCommand command);
}
