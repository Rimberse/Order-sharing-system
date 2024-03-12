package fr.efrei.ordersharingsystem.dataaccess.projections;

import fr.efrei.ordersharingsystem.dataaccess.queries.products.GetCatalogByParkIdQuery;
import fr.efrei.ordersharingsystem.dataaccess.queries.products.GetProductByIdQuery;
import fr.efrei.ordersharingsystem.entity.Product;

import java.util.List;

public interface ProductProjectionService {
    List<Product> handle(GetCatalogByParkIdQuery query);
    Product handle(GetProductByIdQuery query);

}
