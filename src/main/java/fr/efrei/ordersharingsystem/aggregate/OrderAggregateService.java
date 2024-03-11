package fr.efrei.ordersharingsystem.aggregate;

import fr.efrei.ordersharingsystem.commands.orders.AddOrderCommand;
import fr.efrei.ordersharingsystem.commands.orders.ModifyOrderCommand;

public interface OrderAggregateService {
    long handle(AddOrderCommand command);
    void handle(ModifyOrderCommand command);
}
