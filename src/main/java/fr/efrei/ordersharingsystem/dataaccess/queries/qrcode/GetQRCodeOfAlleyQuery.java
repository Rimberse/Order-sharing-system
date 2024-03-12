package fr.efrei.ordersharingsystem.dataaccess.queries.qrcode;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record GetQRCodeOfAlleyQuery(
        @NotBlank @Min(1) Long parkId,
        @NotBlank @Min(1) @Max(20) Integer alleyNumber
) {
}
