package com.app.main.user.serviceImpl;

import com.app.main.exception.custom.ResourceNotFoundException;
import com.app.main.payload.response.PagedResponse;
import com.app.main.user.entity.UserProfile;
import com.app.main.user.mapper.UserProfileMapper;
import com.app.main.user.payload.request.FilterUserProfileListRequest;
import com.app.main.user.payload.request.UserProfileRequest;
import com.app.main.user.payload.request.UserProfileUpdateRequest;
import com.app.main.user.payload.response.UserProfileResponse;
import com.app.main.user.repository.UserProfileRepository;
import com.app.main.user.service.UserProfileService;
import com.app.main.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponse addUserProfile(UserProfileRequest request) {
        UserProfile userProfile = userProfileMapper.toEntity(request);
        return userProfileMapper.toResponse(
                userProfileRepository.save(userProfile)
        );
    }

    @Override
    public PagedResponse<UserProfileResponse> getAllUserProfiles(
            FilterUserProfileListRequest filterRequest) {

        filterRequest.normalize();

        Sort sort = filterRequest.getSortOrder().equalsIgnoreCase("asc")
                ? Sort.by(filterRequest.getSortBy()).ascending()
                : Sort.by(filterRequest.getSortBy()).descending();

        Pageable pageDetails = PageRequest.of(
                filterRequest.getPageNumber(),
                filterRequest.getPageSize(),
                sort
        );

        Page<UserProfile> userProfilePage =
                userProfileRepository.findAll(pageDetails);

        List<UserProfileResponse> content = userProfilePage.getContent()
                .stream()
                .map(userProfileMapper::toResponse)
                .toList();

        return AppUtils.toPageResponse(content, userProfilePage);
    }

    @Override
    public UserProfileResponse getUserProfile(Long id) {
        return userProfileMapper.toResponse(
                getUserProfileById(id)
        );
    }

    @Override
    public UserProfileResponse updateUserProfile(UserProfileUpdateRequest request) {
        UserProfile userProfile = getUserProfileById(request.id());

        userProfile.setFirstName(
                AppUtils.applyString(request.firstName(), userProfile.getFirstName())
        );
        userProfile.setLastName(
                AppUtils.applyString(request.lastName(), userProfile.getLastName())
        );
        userProfile.setEmail(
                AppUtils.applyString(request.email(), userProfile.getEmail())
        );
        userProfile.setPhoneNumber(
                AppUtils.applyString(request.phoneNumber(), userProfile.getPhoneNumber())
        );

        return userProfileMapper.toResponse(
                userProfileRepository.save(userProfile)
        );
    }

    @Override
    public void deleteUserProfile(Long id) {
        UserProfile userProfile = getUserProfileById(id);
        userProfileRepository.delete(userProfile);
    }

    private UserProfile getUserProfileById(Long id) {
        return userProfileRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("UserProfile", "id", id)
                );
    }
}
