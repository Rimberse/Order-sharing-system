package fr.efrei.ordersharingsystem.projections;

import fr.efrei.ordersharingsystem.domain.Product;
import fr.efrei.ordersharingsystem.queries.products.GetProductByIdQuery;

import java.util.List;

public interface ProductProjectionService {
    List<Product> handle();
    Product handle(GetProductByIdQuery query);
}
