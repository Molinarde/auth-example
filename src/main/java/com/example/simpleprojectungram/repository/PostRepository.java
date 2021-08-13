package com.example.simpleprojectungram.repository;

import com.example.simpleprojectungram.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findAllByAuthorId(String authorId);
}
