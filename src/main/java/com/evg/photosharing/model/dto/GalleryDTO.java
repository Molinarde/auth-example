package com.evg.photosharing.model.dto;

import com.evg.photosharing.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GalleryDTO {
    private List<Post> postList;
    private int listTotalSize;
}

