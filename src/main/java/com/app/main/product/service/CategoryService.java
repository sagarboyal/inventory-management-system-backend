package com.app.main.product.service;

import com.app.main.payload.response.PagedResponse;
import com.app.main.product.payload.request.CategoryRequest;
import com.app.main.product.payload.request.CategoryUpdateRequest;
import com.app.main.product.payload.request.CategoryUpdateStatusRequest;
import com.app.main.product.payload.request.FilterCategoryListRequest;
import com.app.main.product.payload.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse addCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> addCategories(List<CategoryRequest> categoryRequests);
    PagedResponse<CategoryResponse> findAllCategories(FilterCategoryListRequest request);
    CategoryResponse updateCategory(CategoryUpdateRequest categoryUpdateRequest);
    void deleteCategory(Long id);
    CategoryResponse updateStatus(CategoryUpdateStatusRequest categoryUpdateStatusRequest);
}
