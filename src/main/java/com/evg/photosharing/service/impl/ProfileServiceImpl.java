package com.evg.photosharing.service.impl;

import com.evg.photosharing.mapper.UserProfileMapper;
import com.evg.photosharing.model.Post;
import com.evg.photosharing.model.User;
import com.evg.photosharing.model.dto.ProfileDTO;
import com.evg.photosharing.service.PostService;
import com.evg.photosharing.service.ProfileService;
import com.evg.photosharing.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final PostService postService;
    private final UserService userService;
    private final UserProfileMapper userProfileMapper;

    @Value("${upload.path}")
    private String uploadPath;

    public ProfileServiceImpl(PostService postService, UserService userService, UserProfileMapper userProfileMapper) {
        this.postService = postService;
        this.userService = userService;
        this.userProfileMapper = userProfileMapper;
    }

    @Override
    public Optional<ProfileDTO> getProfileById(String userId) {
        Optional<User> byId = userService.getById(userId);

        if (byId.isPresent()) {
            User user = byId.get();
            List<Post> allPostByAuthorId = postService.getAllPostByAuthorId(user.getId());
            return Optional.of(userProfileMapper.mapUserToProfile(user, allPostByAuthorId));
        }

        return Optional.empty();
    }
}
