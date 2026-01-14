package com.app.main.product.controller;

import com.app.main.product.payload.request.ProductRequest;
import com.app.main.product.payload.response.ProductResponse;
import com.app.main.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable UUID id, @RequestBody ProductRequest productRequest) {
        ProductResponse updatedProduct =
                productService.updateProduct(id, productRequest);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Product updated successfully");
        response.put("data", updatedProduct);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product deleted successfully");
        return ResponseEntity.ok(response);
    }
}
