package com.example.simpleprojectungram.repository;

import com.example.simpleprojectungram.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findAllByPostId(String postId);
}
