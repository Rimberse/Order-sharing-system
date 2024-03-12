package fr.efrei.ordersharingsystem.domain.aggregators.handlers;

import fr.efrei.ordersharingsystem.domain.aggregators.ProductAggregateService;
import fr.efrei.ordersharingsystem.domain.commands.products.CreateProductCommand;
import fr.efrei.ordersharingsystem.domain.commands.products.DeleteProductCommand;
import fr.efrei.ordersharingsystem.domain.commands.products.ModifyProductCommand;
import fr.efrei.ordersharingsystem.entity.Product;
import fr.efrei.ordersharingsystem.exceptions.ItemNotFoundException;
import fr.efrei.ordersharingsystem.infrastructure.repositories.BowlingParkRepository;
import fr.efrei.ordersharingsystem.infrastructure.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAggregateHandler implements ProductAggregateService {
    private final ProductRepository productRepository;
    private final BowlingParkRepository bowlingParkRepository;
    @Autowired
    public ProductAggregateHandler(ProductRepository productRepository, BowlingParkRepository bowlingParkRepository) {
        this.productRepository = productRepository;
        this.bowlingParkRepository = bowlingParkRepository;
    }

    public long handle(CreateProductCommand command) {
        var product = new Product();
        var park = bowlingParkRepository.findById(command.parkId())
                .orElseThrow(() -> new ItemNotFoundException("BowlingPark", command.parkId()));
        product.setParkId(park.getId());
        product.setName(command.name());
        product.setDescription(command.description());
        product.setPrice(command.price());
        return productRepository.save(product).getId();
    }

    public void handle(ModifyProductCommand command) {
        var product = productRepository.findById(command.id())
                .orElseThrow(() -> new ItemNotFoundException("Product", command.id()));
        var productNotBelongsToPark = !product.getParkId().equals(command.parkId());
        if (productNotBelongsToPark) {
            throw new IllegalArgumentException("Product does not belong to the park");
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
