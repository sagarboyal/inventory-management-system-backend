package com.app.main.product.payload.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductUpdateRequest (
        @NotNull(message = "product id required")
        UUID id,

        @Size(min = 2, max = 150, message = "Product name must be 2 to 150 characters")
        String name,

        @Size(max = 1000, message = "Description max 500 characters")
        String description,

        @Size(max = 80, message = "Brand max 80 characters")
        String brand,

        @DecimalMin(value = "0.01", message = "Price must be greater than 0")
        BigDecimal price,

        @Size(max = 20, message = "Unit max 20 characters")
        String unit,

        @Size(max = 20, message = "Unit max 20 characters")
        Integer quantity,

        @Size(max = 1000, message = "Image url max 1000 characters")
        String imageUrl,

        @Positive(message = "Category id must be positive")
        Long categoryId
) {
}
