package fr.efrei.ordersharingsystem.projections.handlers;

import fr.efrei.ordersharingsystem.domain.Product;
import fr.efrei.ordersharingsystem.projections.ProductProjectionService;
import fr.efrei.ordersharingsystem.queries.GetProductByIdQuery;
import fr.efrei.ordersharingsystem.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductProjectionHandler implements ProductProjectionService {
    private ProductRepository productRepository;

    public ProductProjectionHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> handle() {
        return productRepository.findAll();
    }

    public Product handle(GetProductByIdQuery query) {
        return productRepository.findById(query.productId()).orElse(null);
    }
}
