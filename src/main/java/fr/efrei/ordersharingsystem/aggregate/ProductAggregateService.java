package fr.efrei.ordersharingsystem.aggregate;

import fr.efrei.ordersharingsystem.commands.CreateProductCommand;
import fr.efrei.ordersharingsystem.commands.DeleteProductCommand;
import fr.efrei.ordersharingsystem.commands.ModifyProductCommand;

public interface ProductAggregateService {
    long handle(CreateProductCommand command);
    void handle(ModifyProductCommand command);
    void handle(DeleteProductCommand command);
}
