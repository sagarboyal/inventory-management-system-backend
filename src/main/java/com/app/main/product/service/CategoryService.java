package com.app.main.product.service;

import com.app.main.payload.response.PagedResponse;
import com.app.main.product.payload.request.CategoryRequest;
import com.app.main.product.payload.request.CategoryUpdateRequest;
import com.app.main.product.payload.request.CategoryUpdateStatusRequest;
import com.app.main.product.payload.request.FilterCategoryRequest;
import com.app.main.product.payload.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse addCategory(CategoryRequest categoryRequest);
    PagedResponse<CategoryResponse> findAllCategories(FilterCategoryRequest request);
    CategoryResponse updateCategory(CategoryUpdateRequest categoryUpdateRequest);
    void deleteCategory(Long id);
    CategoryResponse updateStatus(CategoryUpdateStatusRequest categoryUpdateStatusRequest);
}
