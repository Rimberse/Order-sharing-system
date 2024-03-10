package fr.efrei.ordersharingsystem.projections;

import fr.efrei.ordersharingsystem.domain.Product;
import fr.efrei.ordersharingsystem.queries.GetCatalogByParkIdQuery;
import fr.efrei.ordersharingsystem.queries.GetProductByIdQuery;

import java.util.List;

public interface ProductProjectionService {
    List<Product> handle(GetCatalogByParkIdQuery query);
    Product handle(GetProductByIdQuery query);
}
