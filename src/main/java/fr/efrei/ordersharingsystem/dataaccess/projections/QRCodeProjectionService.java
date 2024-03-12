package fr.efrei.ordersharingsystem.dataaccess.projections;

import fr.efrei.ordersharingsystem.dataaccess.queries.qrcode.GetCatalogFromQRCodeQuery;
import fr.efrei.ordersharingsystem.dataaccess.queries.qrcode.GetQRCodeOfAlleyQuery;
import fr.efrei.ordersharingsystem.entity.Product;

import java.util.List;

public interface QRCodeProjectionService {
    List<Product> handle(GetCatalogFromQRCodeQuery query);
    String handle(GetQRCodeOfAlleyQuery query);
}
