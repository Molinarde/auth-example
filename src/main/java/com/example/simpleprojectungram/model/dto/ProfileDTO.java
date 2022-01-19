package com.example.simpleprojectungram.model.dto;

import com.example.simpleprojectungram.model.Post;
import com.example.simpleprojectungram.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Data
public class ProfileDTO {

    private String id;
    private String username;
    private String email;
    private Set<Role> roles;
    private List<Post> post;
}
