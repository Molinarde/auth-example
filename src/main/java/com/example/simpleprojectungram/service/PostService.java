package com.example.simpleprojectungram.service;

import com.example.simpleprojectungram.model.Post;

import java.util.List;

public interface PostService {
    Post insertPost(Post post);
    Post updatePost(Post post);
    Post getById(String id);
    List<Post> getAll();
    List<Post> getAllByAuthorId(String authorId);
}
