package com.app.main.product.serviceImpl;

import com.app.main.exception.custom.ResourceNotFoundException;
import com.app.main.product.entity.Category;
import com.app.main.product.entity.Product;
import com.app.main.product.mapper.ProductMapper;
import com.app.main.product.payload.request.ProductRequest;
import com.app.main.product.payload.response.ProductResponse;
import com.app.main.product.repository.CategoryRepository;
import com.app.main.product.repository.ProductRepository;
import com.app.main.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse addProduct(ProductRequest request) {
        Category category = getCategoryById(request.categoryId());

        Product product = ProductMapper.toEntity(request, category);
        return ProductMapper.toResponse(productRepository.save(product));
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(UUID id) {
        return ProductMapper.toResponse(getProduct(id));
    }

    @Override
    public ProductResponse updateProduct(UUID id, ProductRequest request) {
        Product product = getProduct(id);
        Category category = getCategoryById(request.categoryId());

        ProductMapper.updateEntity(product, request, category);
        return ProductMapper.toResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(UUID id) {
        Product product = getProduct(id);
        productRepository.delete(product);
    }

    private Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "id", id));
    }

    private Product getProduct(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", "id", String.valueOf(id)));
    }
}
