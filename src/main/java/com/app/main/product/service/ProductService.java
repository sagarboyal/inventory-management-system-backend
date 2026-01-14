package com.app.main.product.service;

import com.app.main.payload.response.PagedResponse;
import com.app.main.product.payload.request.FilterProductListRequest;
import com.app.main.product.payload.request.ProductRequest;
import com.app.main.product.payload.request.ProductUpdateRequest;
import com.app.main.product.payload.response.ProductResponse;

import java.util.UUID;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);
    PagedResponse<ProductResponse> getAllProducts(FilterProductListRequest filterRequest);
    ProductResponse updateProduct(ProductUpdateRequest request);
    void deleteProduct(UUID id);
}
