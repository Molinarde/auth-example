package com.example.simpleprojectungram.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document
@Data
public class Comment {

    @Id
    private String id;
    @NotBlank
    private String postId;
    private String authorName;
    @NotBlank
    private String authorId;

    private String parentId;

    @Size(min = 1, max = 60)
    private String text;
}
