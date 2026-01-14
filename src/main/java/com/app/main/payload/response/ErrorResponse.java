package com.app.main.payload.response;

public record ErrorResponse(
        String message,
        String errorCode,
        int status,
        String timestamp,
        String path
) {}