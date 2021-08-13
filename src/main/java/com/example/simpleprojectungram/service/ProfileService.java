package com.example.simpleprojectungram.service;

import com.example.simpleprojectungram.model.Post;
import com.example.simpleprojectungram.model.User;
import com.example.simpleprojectungram.model.dto.ProfileDTO;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final PostService postService;
    private final UserService userService;

    public ProfileService(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    public ProfileDTO getProfile(String userId) {
        User user = userService.getUser(userId);
        List<Post> postList = postService.getAllPostByUserId(userId);

        if (user != null && postList != null) {
            ProfileDTO profileDTO = new ProfileDTO(user, postList);
            return profileDTO;
        }
        return null;
    }

    public Post saveNewPost(Post post) {
        return postService.insertPost(post);
    }

    public Resource loadAsResource(String imageName) {
        return null;
    }

}