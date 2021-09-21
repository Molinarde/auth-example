package com.example.simpleprojectungram.service.impl;

import com.example.simpleprojectungram.model.Post;
import com.example.simpleprojectungram.model.User;
import com.example.simpleprojectungram.model.dto.ProfileDTO;
import com.example.simpleprojectungram.service.PostService;
import com.example.simpleprojectungram.service.ProfileService;
import com.example.simpleprojectungram.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final PostService postService;
    private final UserService userService;

    @Value("${upload.path}")
    private String uploadPath;

    public ProfileServiceImpl(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @Override
    public ProfileDTO getById(String userId) {
        User user = userService.getById(userId);
        List<Post> postList = postService.getAllByAuthorId(userId);

        if (user != null && postList != null) {
            ProfileDTO profileDTO = new ProfileDTO(user, postList);
            return profileDTO;
        }
        return null;
    }

    @Override
    public Post uploadFile(MultipartFile file, Post post){
        if (file.getContentType().equals("image/jpeg")) {
            File uploadDir = new File(uploadPath);

            if (uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String resultFileName = UUID.randomUUID().toString() + "." + file.getOriginalFilename();

            try{
                file.transferTo(new File(uploadPath + "/" + resultFileName));
                post.setImgName(resultFileName);
                return post;
            }
            catch (IOException ioException){
                ioException.printStackTrace();
            }
            return null;
        }
        return null;
    }

    @Override
    public Post addPost(Post post) {
        return postService.insertPost(post);
    }

    @Override
    public Post updatePost(Post post){
        Post postById = postService.getById(post.getId());
        if(postById != null){
            return postService.updatePost(post);
        }
        return null;
    }
}