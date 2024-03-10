package fr.efrei.ordersharingsystem.queries.products;

import jakarta.validation.constraints.NotBlank;

public record GetProductByIdQuery(
        Long parkId,
        @NotBlank Long productId) {
    public static record GetCatalogByParkIdQuery(Long parkId) {
    }
}
