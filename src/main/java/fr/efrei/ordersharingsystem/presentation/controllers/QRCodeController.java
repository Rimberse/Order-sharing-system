package fr.efrei.ordersharingsystem.presentation.controllers;

import fr.efrei.ordersharingsystem.entity.Product;
import fr.efrei.ordersharingsystem.dataaccess.projections.QRCodeProjectionService;
import fr.efrei.ordersharingsystem.dataaccess.queries.qrcode.GetCatalogFromQRCodeQuery;
import fr.efrei.ordersharingsystem.dataaccess.queries.qrcode.GetQRCodeOfAlleyQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class QRCodeController {
    private final QRCodeProjectionService qrCodeProjectionService;

    @Autowired
    public QRCodeController(QRCodeProjectionService qrCodeProjectionService) {
        this.qrCodeProjectionService = qrCodeProjectionService;
    }
    @GetMapping("/catalogForQRCode/{qrCode}")
    public List<Product> getCatalog(@PathVariable String qrCode) {
        var query = new GetCatalogFromQRCodeQuery(qrCode);
        return qrCodeProjectionService.handle(query);
    }
    @GetMapping("/qrCode")
    public String getQRCode(@RequestParam(name = "park_id") Long parkId, @RequestParam(name = "alley_number") Integer alleyNumber) {
        var query = new GetQRCodeOfAlleyQuery(parkId, alleyNumber);
        return qrCodeProjectionService.handle(query);
    }
}
