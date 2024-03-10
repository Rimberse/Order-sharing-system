package fr.efrei.ordersharingsystem.projections.handlers;

import fr.efrei.ordersharingsystem.domain.Product;
import fr.efrei.ordersharingsystem.projections.ProductProjectionService;
import fr.efrei.ordersharingsystem.queries.products.GetProductByIdQuery;
import fr.efrei.ordersharingsystem.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductProjectionHandler implements ProductProjectionService {

    @Autowired
    private final ProductRepository productRepository;

    public List<Product> handle() {
        return productRepository.findAll();
    }

    public Product handle(GetProductByIdQuery query) {
        return productRepository.findById(query.productId()).orElse(null);
    }
}
