package com.example.simpleprojectungram.service;

import com.example.simpleprojectungram.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> addComment(Comment comment);
    void removeComment(String id);
    void updateComment(Comment comment);
    List<Comment> getAllComment();
    Optional<Comment> getCommentById(String id);
    List<Comment> getCommentByPostId(String id);
}
