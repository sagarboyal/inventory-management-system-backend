package com.app.main.user.controller;

import com.app.main.payload.response.PagedResponse;
import com.app.main.user.payload.request.FilterUserProfileListRequest;
import com.app.main.user.payload.request.UserProfileRequest;
import com.app.main.user.payload.request.UserProfileUpdateRequest;
import com.app.main.user.payload.response.UserProfileResponse;
import com.app.main.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-profiles")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping
    public ResponseEntity<UserProfileResponse> addUserProfile(
            @RequestBody UserProfileRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userProfileService.addUserProfile(request));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<UserProfileResponse>> getAllUserProfiles(@RequestBody FilterUserProfileListRequest filterRequest) {
        return ResponseEntity.ok(userProfileService.getAllUserProfiles(filterRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponse> getUserProfile(
            @PathVariable Long id) {
        return ResponseEntity.ok(userProfileService.getUserProfile(id));
    }

    @PutMapping
    public ResponseEntity<UserProfileResponse> updateUserProfile(
            @RequestBody UserProfileUpdateRequest request) {
        return ResponseEntity.ok(userProfileService.updateUserProfile(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfile(
            @PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
        return ResponseEntity.noContent().build();
    }
}
