package fr.efrei.ordersharingsystem.aggregate.handlers;

import fr.efrei.ordersharingsystem.aggregate.ProductAggregateService;
import fr.efrei.ordersharingsystem.commands.products.CreateProductCommand;
import fr.efrei.ordersharingsystem.commands.products.DeleteProductCommand;
import fr.efrei.ordersharingsystem.commands.products.ModifyProductCommand;
import fr.efrei.ordersharingsystem.domain.Product;
import fr.efrei.ordersharingsystem.exceptions.ItemNotFoundException;
import fr.efrei.ordersharingsystem.repositories.BowlingParkRepository;
import fr.efrei.ordersharingsystem.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductAggregateHandler implements ProductAggregateService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final BowlingParkRepository bowlingParkRepository;

    public long handle(CreateProductCommand command) {
        var product = new Product();
        var park = bowlingParkRepository.findById(command.parkId()).orElse(null);
        if (park == null) {
            throw new ItemNotFoundException("BowlingPark", command.parkId());
        }
        product.setParkId(park.getId());
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
