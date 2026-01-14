package com.app.main.product.serviceImpl;

import com.app.main.exception.custom.ResourceNotFoundException;
import com.app.main.payload.response.PagedResponse;
import com.app.main.product.entity.Category;
import com.app.main.product.entity.Product;
import com.app.main.product.mapper.ProductMapper;
import com.app.main.product.payload.request.FilterProductListRequest;
import com.app.main.product.payload.request.ProductRequest;
import com.app.main.product.payload.request.ProductUpdateRequest;
import com.app.main.product.payload.response.ProductResponse;
import com.app.main.product.repository.CategoryRepository;
import com.app.main.product.repository.ProductRepository;
import com.app.main.product.service.ProductService;
import com.app.main.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse addProduct(ProductRequest request) {
        Category category = getCategoryById(request.categoryId());

        Product product = productMapper.toEntity(request);
        product.setCategory(category);

        return productMapper.toResponse(
                productRepository.save(product)
        );
    }

    @Override
    public PagedResponse<ProductResponse> getAllProducts(
            FilterProductListRequest filterRequest) {

        Sort sort = filterRequest.getSortOrder().equalsIgnoreCase("asc")
                ? Sort.by(filterRequest.getSortBy()).ascending()
                : Sort.by(filterRequest.getSortBy()).descending();

        Pageable pageDetails = PageRequest.of(
                filterRequest.getPageNumber(),
                filterRequest.getPageSize(),
                sort
        );

        Page<Product> productPage = productRepository.findAll(pageDetails);

        List<ProductResponse> content = productPage.getContent()
                .stream()
                .map(productMapper::toResponse)
                .toList();

        return AppUtils.toPageResponse(content, productPage);
    }

    @Override
    public ProductResponse updateProduct(ProductUpdateRequest request) {
        Product product = getProduct(request.id());

        product.setName(
                AppUtils.applyString(request.name(), product.getName())
        );
        product.setDescription(
                AppUtils.applyString(request.description(), product.getDescription())
        );
        product.setBrand(
                AppUtils.applyString(request.brand(), product.getBrand())
        );
        product.setPrice(
                AppUtils.applyValue(request.price(), product.getPrice())
        );
        product.setUnit(
                AppUtils.applyString(request.unit(), product.getUnit())
        );
        product.setQuantity(
                AppUtils.applyValue(request.quantity(), product.getQuantity())
        );
        product.setImageUrl(
                AppUtils.applyString(request.imageUrl(), product.getImageUrl())
        );

        if (request.categoryId() != null) {
            Category category = getCategoryById(request.categoryId());
            product.setCategory(category);
        }

        return productMapper.toResponse(
                productRepository.save(product)
        );
    }

    @Override
    public void deleteProduct(UUID id) {
        Product product = getProduct(id);
        productRepository.delete(product);
    }

    private Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "id", id)
                );
    }

    private Product getProduct(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product", "id", String.valueOf(id)
                        )
                );
    }
}
