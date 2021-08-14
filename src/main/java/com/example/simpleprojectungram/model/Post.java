package com.example.simpleprojectungram.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document
@Data
public class Post {
    @Id
    private String id;
    @Size(min = 30, max = 300)
    private String content;
    @NotBlank
    private String authorId;

    private String imgName;

}
