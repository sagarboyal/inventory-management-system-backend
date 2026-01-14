package com.app.main.product.payload.request;

import com.app.main.product.constants.AppConstant;
import lombok.Getter;

@Getter
public class FilterCategoryListRequest {

    private Integer pageNumber = AppConstant.DEFAULT_PAGE_NUMBER;
    private Integer pageSize = AppConstant.DEFAULT_PAGE_SIZE;
    private String sortBy = AppConstant.DEFAULT_SORT_ORDER_CATEGORY;
    private String sortOrder = AppConstant.DEFAULT_SORT_DIR;

    public void normalize() {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = AppConstant.DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize <= 0) {
            pageSize = AppConstant.DEFAULT_PAGE_SIZE;
        }

        if (sortBy == null || sortBy.isBlank()) {
            sortBy = AppConstant.DEFAULT_SORT_ORDER_CATEGORY;
        }

        if (sortOrder == null || sortOrder.isBlank()) {
            sortOrder = AppConstant.DEFAULT_SORT_DIR;
        }
    }
}

