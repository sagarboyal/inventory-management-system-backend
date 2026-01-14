package com.app.main.product.service;

import com.app.main.product.payload.request.CategoryRequest;
import com.app.main.product.payload.request.CategoryUpdateRequest;
import com.app.main.product.payload.request.CategoryUpdateStatusRequest;
import com.app.main.product.payload.response.CategoryResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface CategoryService {
    CategoryResponse addCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> findAllCategories();
    CategoryResponse findCategoryById(Long id);
    CategoryResponse updateCategory(CategoryUpdateRequest categoryUpdateRequest);
    void deleteCategory(Long id);
    CategoryResponse updateStatus(CategoryUpdateStatusRequest categoryUpdateStatusRequest);
}
