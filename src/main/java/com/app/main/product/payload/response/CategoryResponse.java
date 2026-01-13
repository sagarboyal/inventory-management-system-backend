package com.app.main.product.payload.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record CategoryResponse(Long id, String name, Boolean status, Instant createdOn) {
}
