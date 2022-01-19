package com.example.simpleprojectungram.mapper;

import com.example.simpleprojectungram.model.Post;
import com.example.simpleprojectungram.model.User;
import com.example.simpleprojectungram.model.dto.ProfileDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserProfileMapper {

    public ProfileDTO mapUserToProfile(User user, List<Post> postList) {
        return new ProfileDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                postList);
    }
}
