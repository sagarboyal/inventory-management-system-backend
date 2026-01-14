package com.app.main.utils;

import com.app.main.payload.response.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppUtils {

    public static <T> PagedResponse<T> toPageResponse(List<T> content, Page<?> page){
        return PagedResponse.<T>builder()
                .content(content)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .lastPage(page.isLast())
                .build();
    }

    public static String applyString(String newVal, String oldVal) {
        return (newVal != null && !newVal.isBlank()) ? newVal.trim() : oldVal;
    }

    public static <T> T applyValue(T newVal, T oldVal) {
        return newVal != null ? newVal : oldVal;
    }
}
