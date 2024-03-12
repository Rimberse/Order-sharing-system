package fr.efrei.ordersharingsystem.dataaccess.queries.qrcode;

import jakarta.validation.constraints.NotBlank;

public record GetCatalogFromQRCodeQuery(
        @NotBlank String qrCode
) {
}
