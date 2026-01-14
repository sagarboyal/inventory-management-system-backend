package com.app.main.product.service;

import com.app.main.product.payload.request.ProductRequest;
import com.app.main.product.payload.response.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(UUID id);

    ProductResponse updateProduct(UUID id, ProductRequest productRequest);

    void deleteProduct(UUID id);
}
