package com.app.main.product.serviceImpl;

import com.app.main.exception.custom.ResourceNotFoundException;
import com.app.main.payload.response.PagedResponse;
import com.app.main.product.entity.Category;
import com.app.main.product.mapper.CategoryMapper;
import com.app.main.utils.AppUtils;
import com.app.main.product.payload.request.CategoryRequest;
import com.app.main.product.payload.request.CategoryUpdateRequest;
import com.app.main.product.payload.request.CategoryUpdateStatusRequest;
import com.app.main.product.payload.request.FilterCategoryRequest;
import com.app.main.product.payload.response.CategoryResponse;
import com.app.main.product.repository.CategoryRepository;
import com.app.main.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PagedResponse<CategoryResponse> findAllCategories(FilterCategoryRequest request) {
        Sort sort = request.getSortOrder().equalsIgnoreCase("asc")
                ? Sort.by(request.getSortBy()).ascending()
                : Sort.by(request.getSortBy()).descending();

        Pageable pageDetails = PageRequest.of(request.getPageNumber(), request.getPageSize(), sort);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);

        List<CategoryResponse> categoryList = categoryPage
                .getContent()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return AppUtils.toPageResponse(categoryList, categoryPage);
    }

    @Override
    public CategoryResponse updateCategory(CategoryUpdateRequest categoryUpdateRequest) {
        Category category = getCategoryById(categoryUpdateRequest.id());

        category.setName(categoryUpdateRequest.name());
        return mapper.toResponse(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        getCategoryById(id);
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse updateStatus(CategoryUpdateStatusRequest categoryUpdateStatusRequest) {
        Category category = getCategoryById(categoryUpdateStatusRequest.id());
        category.setStatus(categoryUpdateStatusRequest.status());
        return mapper.toResponse(categoryRepository.save(category));
    }

    private Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    }
}
