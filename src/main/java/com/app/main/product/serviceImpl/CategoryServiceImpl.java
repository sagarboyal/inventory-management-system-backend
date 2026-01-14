package com.app.main.product.serviceImpl;

import com.app.main.exception.custom.ResourceNotFoundException;
import com.app.main.product.entity.Category;
import com.app.main.product.mapper.CategoryMapper;
import com.app.main.product.payload.request.CategoryRequest;
import com.app.main.product.payload.request.CategoryUpdateRequest;
import com.app.main.product.payload.response.CategoryResponse;
import com.app.main.product.repository.CategoryRepository;
import com.app.main.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.name());
        return mapper.toResponse(categoryRepository.save(category));
    }

    @Override
    public List<CategoryResponse> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse findCategoryById(Long id) {
        Category category = getCategoryById(id);
        return mapper.toResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(CategoryUpdateRequest categoryUpdateRequest) {
        Category category = getCategoryById(categoryUpdateRequest.id());

        category.setName(categoryUpdateRequest.name());
        return mapper.toResponse(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    private Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    }
}
