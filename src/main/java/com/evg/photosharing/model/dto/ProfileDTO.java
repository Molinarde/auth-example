package com.evg.photosharing.model.dto;

import com.evg.photosharing.model.Post;
import com.evg.photosharing.model.Role;
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
