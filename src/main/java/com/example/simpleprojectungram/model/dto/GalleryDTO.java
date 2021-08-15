package com.example.simpleprojectungram.model.dto;

import com.example.simpleprojectungram.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GalleryDTO {
    private List<Post> postList;
    private int listTotalSize;
}

