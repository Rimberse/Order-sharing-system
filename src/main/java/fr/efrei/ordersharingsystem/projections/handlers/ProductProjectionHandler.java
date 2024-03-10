package fr.efrei.ordersharingsystem.projections.handlers;

import fr.efrei.ordersharingsystem.domain.Product;
import fr.efrei.ordersharingsystem.projections.ProductProjectionService;
import fr.efrei.ordersharingsystem.queries.GetCatalogByParkIdQuery;
import fr.efrei.ordersharingsystem.queries.GetProductByIdQuery;
import fr.efrei.ordersharingsystem.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ProductProjectionHandler implements ProductProjectionService {

    @Autowired
    private final ProductRepository productRepository;

    public List<Product> handle(GetCatalogByParkIdQuery query) {
        return productRepository.findAllByPark_Id(query.parkId());
    }

    public Product handle(GetProductByIdQuery query) {
        var product = productRepository.findById(query.productId()).orElse(null);
        if (product == null) {
            return null;
        }
        if (!Objects.equals(product.getPark().getId(), query.parkId())) {
            return null;
        }
        return product;
    }
}
