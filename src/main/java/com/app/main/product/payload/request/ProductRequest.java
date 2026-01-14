package com.app.main.product.payload.request;

import java.math.BigDecimal;

public record ProductRequest(String sku,
                             String name,
                             String description,
                             String brand,
                             BigDecimal price,
                             String unit,
                             Integer quantity,
                             String imageUrl,
                             Long categoryId) {
}
