package fr.efrei.ordersharingsystem.queries.qrcode;

import jakarta.validation.constraints.NotBlank;

public record GetCatalogFromQRCodeQuery(
        @NotBlank String qrCode
) {
}
