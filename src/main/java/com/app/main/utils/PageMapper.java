package com.app.main.utils;

import com.app.main.payload.response.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageMapper {

    public static <T> PagedResponse<T> toResponse(List<T> content, Page<?> page){
        return PagedResponse.<T>builder()
                .content(content)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .lastPage(page.isLast())
                .build();
    }

}
