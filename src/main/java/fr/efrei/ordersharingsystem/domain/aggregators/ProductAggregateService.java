package fr.efrei.ordersharingsystem.domain.aggregators;

import fr.efrei.ordersharingsystem.domain.commands.products.CreateProductCommand;
import fr.efrei.ordersharingsystem.domain.commands.products.DeleteProductCommand;
import fr.efrei.ordersharingsystem.domain.commands.products.ModifyProductCommand;

public interface ProductAggregateService {
    long handle(CreateProductCommand command);
    void handle(ModifyProductCommand command);
    void handle(DeleteProductCommand command);
}
