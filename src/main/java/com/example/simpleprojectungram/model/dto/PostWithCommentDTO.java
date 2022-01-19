package com.example.simpleprojectungram.model.dto;

import com.example.simpleprojectungram.model.Comment;
import com.example.simpleprojectungram.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PostWithCommentDTO {
    Post post;
    List<Comment> commentList;
}
