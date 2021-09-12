package com.example.simpleprojectungram.model.dto;

import com.example.simpleprojectungram.model.Post;
import com.example.simpleprojectungram.model.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ProfileDTO {
    private Users user;
    private List<Post> post;
}
