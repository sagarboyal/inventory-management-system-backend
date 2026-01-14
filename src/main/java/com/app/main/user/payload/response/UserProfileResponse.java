package com.app.main.user.payload.response;

import lombok.Builder;

@Builder
public record UserProfileResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
}
