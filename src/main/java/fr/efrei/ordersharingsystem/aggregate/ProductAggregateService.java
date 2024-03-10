package fr.efrei.ordersharingsystem.aggregate;

import fr.efrei.ordersharingsystem.commands.products.CreateProductCommand;
import fr.efrei.ordersharingsystem.commands.products.DeleteProductCommand;
import fr.efrei.ordersharingsystem.commands.products.ModifyProductCommand;

public interface ProductAggregateService {
    long handle(CreateProductCommand command);
    void handle(ModifyProductCommand command);
    void handle(DeleteProductCommand command);
}
