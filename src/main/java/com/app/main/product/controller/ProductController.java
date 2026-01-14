package com.app.main.product.controller;

import com.app.main.payload.response.PagedResponse;
import com.app.main.product.payload.request.FilterProductListRequest;
import com.app.main.product.payload.request.ProductRequest;
import com.app.main.product.payload.request.ProductUpdateRequest;
import com.app.main.product.payload.response.ProductResponse;
import com.app.main.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.addProduct(productRequest));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<ProductResponse>> getAllProducts(@RequestBody FilterProductListRequest filterRequest) {
        filterRequest.normalize();
        return ResponseEntity.ok(productService.getAllProducts(filterRequest));
    }

    @PutMapping
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductUpdateRequest request) {
        return ResponseEntity.ok(productService.updateProduct(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
