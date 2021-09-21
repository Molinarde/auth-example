package com.example.simpleprojectungram.service;

import com.example.simpleprojectungram.model.Post;
import com.example.simpleprojectungram.model.dto.ProfileDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {
    ProfileDTO getById(String userId);
    Post uploadFile(MultipartFile file, Post post);
    Post addPost(Post post);
    Post updatePost(Post post);
}
