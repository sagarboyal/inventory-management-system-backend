package com.app.main.user.service;

import com.app.main.payload.response.PagedResponse;
import com.app.main.user.payload.request.FilterUserProfileListRequest;
import com.app.main.user.payload.request.UserProfileRequest;
import com.app.main.user.payload.request.UserProfileUpdateRequest;
import com.app.main.user.payload.response.UserProfileResponse;
import org.jspecify.annotations.Nullable;

public interface UserProfileService {
    UserProfileResponse addUserProfile(UserProfileRequest request);

    PagedResponse<UserProfileResponse> getAllUserProfiles(
            FilterUserProfileListRequest filterRequest);

    UserProfileResponse getUserProfile(Long id);

    UserProfileResponse updateUserProfile(UserProfileUpdateRequest request);

    void deleteUserProfile(Long id);
}
