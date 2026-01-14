package com.app.main.user.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterUserProfileListRequest {

    private int pageNumber = 0;
    private int pageSize = 10;
    private String sortBy = "id";
    private String sortOrder = "desc";

    public void normalize() {
        if (pageNumber < 0) pageNumber = 0;
        if (pageSize <= 0) pageSize = 10;
        if (sortBy == null || sortBy.isBlank()) sortBy = "id";
        if (!sortOrder.equalsIgnoreCase("asc")) sortOrder = "desc";
    }
}
