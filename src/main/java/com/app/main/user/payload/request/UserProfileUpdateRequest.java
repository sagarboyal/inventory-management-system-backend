package com.app.main.user.payload.request;

import jakarta.validation.constraints.*;

public record UserProfileUpdateRequest(

        @NotNull(message = "User profile id is required")
        Long id,

        @Size(min = 2, max = 50, message = "First name must be 2 to 50 characters")
        String firstName,

        @Size(min = 2, max = 50, message = "Last name must be 2 to 50 characters")
        String lastName,

        @Email(message = "Email must be valid")
        @Size(max = 100, message = "Email max 100 characters")
        String email,

        @Pattern(
                regexp = "^[0-9]{10}$",
                message = "Phone number must be exactly 10 digits"
        )
        String phoneNumber
) {
}
