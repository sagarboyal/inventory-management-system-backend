package com.app.main.product.mapper;

import com.app.main.product.entity.Category;
import com.app.main.product.payload.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        if (category == null) return null;

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .status(category.getStatus())
                .createdOn(category.getCreatedOn())
                .build();
    }
}

