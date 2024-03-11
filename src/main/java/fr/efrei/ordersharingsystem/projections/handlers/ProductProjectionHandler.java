package fr.efrei.ordersharingsystem.projections.handlers;

import fr.efrei.ordersharingsystem.domain.Product;
import fr.efrei.ordersharingsystem.projections.ProductProjectionService;
import fr.efrei.ordersharingsystem.queries.products.GetCatalogByParkIdQuery;
import fr.efrei.ordersharingsystem.queries.products.GetProductByIdQuery;
import fr.efrei.ordersharingsystem.repositories.BowlingParkRepository;
import fr.efrei.ordersharingsystem.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductProjectionHandler implements ProductProjectionService {

    private final ProductRepository productRepository;
    private final BowlingParkRepository bowlingParkRepository;

    @Autowired
    public ProductProjectionHandler(ProductRepository productRepository, BowlingParkRepository bowlingParkRepository) {
        this.productRepository = productRepository;
        this.bowlingParkRepository = bowlingParkRepository;
    }

    public List<Product> handle(GetCatalogByParkIdQuery query) {
        var park = bowlingParkRepository.findById(query.parkId()).orElse(null);
        if (park == null) {
            return new ArrayList<>() ;
        }
        return park.getProducts();
    }

    public Product handle(GetProductByIdQuery query) {
        var product = productRepository.findById(query.productId()).orElse(null);
        if (product == null) {
            return null;
        }
        if (!Objects.equals(product.getParkId(), query.parkId())) {
            return null;
        }
        return product;
    }
}
