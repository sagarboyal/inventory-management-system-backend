package com.app.main.product.mapper;

import com.app.main.product.entity.Category;
import com.app.main.product.entity.Product;
import com.app.main.product.payload.request.ProductRequest;
import com.app.main.product.payload.response.ProductResponse;

public class ProductMapper {

    public static Product toEntity(ProductRequest request, Category category) {
        Product product = new Product();
        product.setSku(request.sku());
        product.setName(request.name());
        product.setDescription(request.description());
        product.setBrand(request.brand());
        product.setPrice(request.price());
        product.setUnit(request.unit());
        product.setQuantity(request.quantity());
        product.setImageUrl(request.imageUrl());
        product.setCategory(category);
        return product;
    }

    public static void updateEntity(
            Product product,
            ProductRequest request,
            Category category) {

        product.setSku(request.sku());
        product.setName(request.name());
        product.setDescription(request.description());
        product.setBrand(request.brand());
        product.setPrice(request.price());
        product.setUnit(request.unit());
        product.setQuantity(request.quantity());
        product.setImageUrl(request.imageUrl());
        product.setCategory(category);
    }

    public static ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .price(product.getPrice())
                .unit(product.getUnit())
                .quantity(product.getQuantity())
                .imageUrl(product.getImageUrl())
                .categoryName(product.getCategory().getName())
                .build();
    }
}
