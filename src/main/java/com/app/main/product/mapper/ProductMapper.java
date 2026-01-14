package com.app.main.product.mapper;

import com.app.main.product.entity.Category;
import com.app.main.product.entity.Product;
import com.app.main.product.payload.request.ProductRequest;
import com.app.main.product.payload.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setBrand(request.brand());
        product.setPrice(request.price());
        product.setUnit(request.unit());
        product.setQuantity(request.quantity());
        product.setImageUrl(request.imageUrl());
        return product;
    }

    public ProductResponse toResponse(Product product) {
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
