package com.example.simpleprojectungram.service;

import com.example.simpleprojectungram.model.Post;
import com.example.simpleprojectungram.model.User;
import com.example.simpleprojectungram.model.dto.RecommendationDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GalleryService {

    private final PostService postService;
    private final UserService userService;

    public GalleryService(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    public List<Post> findAllPosts() {
        return postService.getAllPost();
    }


    public List<RecommendationDTO> getRecommendation() {
        Random rand = new Random();
        List<User> all = userService.findAll();
        if (all.size() > 5) {
            int i = rand.nextInt(all.size() - 5);
            List<RecommendationDTO> collect = all
                    .stream()
                    .skip(i)
                    .limit(5)
                    .map(user -> {
                        RecommendationDTO recommendationDTO = new RecommendationDTO();
                        recommendationDTO.setUserId(user.getId());
                        recommendationDTO.setUsername(user.getUsername());
                        recommendationDTO.setImgURL("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
                        return recommendationDTO;
                    })
                    .collect(Collectors.toList());
            return collect;
        }
        List<RecommendationDTO> recommendationDTOS = all.stream()
                .map(user -> {
                    RecommendationDTO recommendationDTO = new RecommendationDTO();
                    recommendationDTO.setUserId(user.getId());
                    recommendationDTO.setUsername(user.getUsername());
                    recommendationDTO.setImgURL("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
                    return recommendationDTO;
                })
                .collect(Collectors.toList());
        return recommendationDTOS;
    }
}
