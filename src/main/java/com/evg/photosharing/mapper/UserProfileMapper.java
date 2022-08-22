package com.evg.photosharing.mapper;

import com.evg.photosharing.model.Post;
import com.evg.photosharing.model.User;
import com.evg.photosharing.model.dto.ProfileDTO;
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
