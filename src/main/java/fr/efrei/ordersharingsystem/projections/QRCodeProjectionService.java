package fr.efrei.ordersharingsystem.projections;

import fr.efrei.ordersharingsystem.domain.Product;
import fr.efrei.ordersharingsystem.queries.qrcode.GetCatalogFromQRCodeQuery;
import fr.efrei.ordersharingsystem.queries.qrcode.GetQRCodeOfAlleyQuery;

import java.util.List;

public interface QRCodeProjectionService {
    List<Product> handle(GetCatalogFromQRCodeQuery query);
    String handle(GetQRCodeOfAlleyQuery query);
}
