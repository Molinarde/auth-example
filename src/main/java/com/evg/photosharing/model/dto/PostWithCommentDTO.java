package com.evg.photosharing.model.dto;

import com.evg.photosharing.model.Comment;
import com.evg.photosharing.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PostWithCommentDTO {
    Post post;
    List<Comment> commentList;
}
