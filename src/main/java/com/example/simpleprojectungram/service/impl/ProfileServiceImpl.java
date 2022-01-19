package com.example.simpleprojectungram.service.impl;

import com.example.simpleprojectungram.mapper.UserProfileMapper;
import com.example.simpleprojectungram.model.Post;
import com.example.simpleprojectungram.model.User;
import com.example.simpleprojectungram.model.dto.ProfileDTO;
import com.example.simpleprojectungram.service.PostService;
import com.example.simpleprojectungram.service.ProfileService;
import com.example.simpleprojectungram.service.UserService;
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
            Optional<ProfileDTO> profileDTO = Optional.of(userProfileMapper.mapUserToProfile(user, allPostByAuthorId));
            return profileDTO;
        }

        return Optional.empty();
    }
}
