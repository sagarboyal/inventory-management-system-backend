package com.app.main.user.mapper;

import com.app.main.user.entity.UserProfile;
import com.app.main.user.payload.request.UserProfileRequest;
import com.app.main.user.payload.response.UserProfileResponse;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {

    public UserProfile toEntity(UserProfileRequest request) {
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(request.firstName());
        userProfile.setLastName(request.lastName());
        userProfile.setEmail(request.email());
        userProfile.setPhoneNumber(request.phoneNumber());
        return userProfile;
    }

    public UserProfileResponse toResponse(UserProfile userProfile) {
        return UserProfileResponse.builder()
                .id(userProfile.getId())
                .firstName(userProfile.getFirstName())
                .lastName(userProfile.getLastName())
                .email(userProfile.getEmail())
                .phoneNumber(userProfile.getPhoneNumber())
                .build();
    }
}
