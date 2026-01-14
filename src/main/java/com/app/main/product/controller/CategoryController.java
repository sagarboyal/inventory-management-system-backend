package com.app.main.product.controller;

import com.app.main.payload.response.PagedResponse;
import com.app.main.product.payload.request.CategoryRequest;
import com.app.main.product.payload.request.CategoryUpdateRequest;
import com.app.main.product.payload.request.CategoryUpdateStatusRequest;
import com.app.main.product.payload.request.FilterCategoryListRequest;
import com.app.main.product.payload.response.CategoryResponse;
import com.app.main.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(categoryRequest));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<CategoryResponse>> getAllCategories(
            @RequestBody FilterCategoryListRequest request
            ) {
        request.normalize();
        return ResponseEntity.ok(categoryService.findAllCategories(request));
    }

    @PutMapping
    public ResponseEntity<CategoryResponse> updateCategory(
            @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
            return ResponseEntity.ok(categoryService.updateCategory(categoryUpdateRequest));
    }

    @PatchMapping
    public ResponseEntity<CategoryResponse> updateStatusHandler(@RequestBody CategoryUpdateStatusRequest categoryUpdateStatusRequest){
        return ResponseEntity.ok(categoryService.updateStatus(categoryUpdateStatusRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
