package fr.efrei.ordersharingsystem.aggregate.handlers;

import fr.efrei.ordersharingsystem.aggregate.ProductAggregateService;
import fr.efrei.ordersharingsystem.commands.CreateProductCommand;
import fr.efrei.ordersharingsystem.commands.DeleteProductCommand;
import fr.efrei.ordersharingsystem.commands.ModifyProductCommand;
import fr.efrei.ordersharingsystem.domain.Product;
import fr.efrei.ordersharingsystem.exceptions.ItemNotFoundException;
import fr.efrei.ordersharingsystem.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductAggregateHandler implements ProductAggregateService {

    @Autowired
    ProductRepository productRepository;

    public long handle(CreateProductCommand command) {
        var product = new Product();
        product.setName(command.name());
        product.setDescription(command.description());
        product.setPrice(command.price());
        return productRepository.save(product).getId();
    }

    public void handle(ModifyProductCommand command) {
        var product = productRepository.findById(command.id()).orElse(null);
        if (product == null) {
            throw new ItemNotFoundException("Product", command.id());
        }
        product.setName(command.name());
        product.setDescription(command.description());
        product.setPrice(command.price());
        productRepository.save(product);
    }

    public void handle(DeleteProductCommand command) {
        productRepository.deleteById(command.id());
    }
}
