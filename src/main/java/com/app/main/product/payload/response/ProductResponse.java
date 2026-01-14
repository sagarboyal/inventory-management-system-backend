package com.app.main.product.payload.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProductResponse(
        UUID id,
        String sku,
        String name,
        String description,
        String brand,
        BigDecimal price,
        String unit,
        Integer quantity,
        String imageUrl,
        String categoryName) {
}
