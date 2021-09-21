package com.example.simpleprojectungram.service.impl;

import com.example.simpleprojectungram.model.Post;
import com.example.simpleprojectungram.repository.PostRepository;
import com.example.simpleprojectungram.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllByAuthorId(String authorId) {
        return postRepository.findAllByAuthorId(authorId);
    }

    @Override
    public Post insertPost(Post post) {
        return postRepository.insert(post);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post getById(String id){
        return postRepository.findById(id).get();
    }

    @Override
    public Post updatePost(Post post){
        return postRepository.save(post);
    }
}
