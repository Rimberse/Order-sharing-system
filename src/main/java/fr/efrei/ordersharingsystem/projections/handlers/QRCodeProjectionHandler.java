package fr.efrei.ordersharingsystem.projections.handlers;

import fr.efrei.ordersharingsystem.domain.Product;
import fr.efrei.ordersharingsystem.projections.QRCodeProjectionService;
import fr.efrei.ordersharingsystem.queries.products.GetCatalogByParkIdQuery;
import fr.efrei.ordersharingsystem.queries.qrcode.GetCatalogFromQRCodeQuery;
import fr.efrei.ordersharingsystem.queries.qrcode.GetQRCodeOfAlleyQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QRCodeProjectionHandler implements QRCodeProjectionService {
    private final ProductProjectionHandler productProjectionHandler;
    @Autowired
    public QRCodeProjectionHandler(ProductProjectionHandler productProjectionHandler) {
        this.productProjectionHandler = productProjectionHandler;
    }

    public List<Product> handle(GetCatalogFromQRCodeQuery query) {
        var codes = query.qrCode().split("-");
        var parkId = Long.parseLong(codes[0]);
        var parkIdQuery = new GetCatalogByParkIdQuery(parkId);
        return productProjectionHandler.handle(parkIdQuery);
    }

    public String handle(GetQRCodeOfAlleyQuery query) {
        return query.parkId() + "-" + query.alleyNumber();
    }
}
