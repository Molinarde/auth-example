package com.example.simpleprojectungram.service;

import com.example.simpleprojectungram.model.Post;
import com.example.simpleprojectungram.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class PostService {
    
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPostByUserId(String userId) {
        return postRepository.findAllByAuthorId(userId);
    }

    public Post insertPost(Post post) {
        return postRepository.insert(post);
    }


    public List<Post> getAllPost() {
        return postRepository.findAll();
    }
}
