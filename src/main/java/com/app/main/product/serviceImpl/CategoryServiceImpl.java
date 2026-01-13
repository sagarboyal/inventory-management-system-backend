package com.app.main.product.serviceImpl;

import com.app.main.product.entity.Category;
import com.app.main.product.payload.request.CategoryRequest;
import com.app.main.product.payload.request.CategoryUpdateRequest;
import com.app.main.product.payload.response.CategoryResponse;
import com.app.main.product.repository.CategoryRepository;
import com.app.main.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.name());
        return convertToResponse(categoryRepository.save(category));
    }

    @Override
    public List<CategoryResponse> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public CategoryResponse findCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return convertToResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(CategoryUpdateRequest categoryUpdateRequest) {
        Category category = categoryRepository.findById(categoryUpdateRequest.id())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryUpdateRequest.id()));

        category.setName(categoryUpdateRequest.name());
        return convertToResponse(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    private CategoryResponse convertToResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .status(category.getStatus())
                .createdOn(category.getCreatedOn())
                .build();
    }
}
