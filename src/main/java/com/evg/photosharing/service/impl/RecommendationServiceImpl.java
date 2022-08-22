package com.evg.photosharing.service.impl;

import com.evg.photosharing.model.User;
import com.evg.photosharing.model.dto.RecommendationDTO;
import com.evg.photosharing.service.RecommendationService;
import com.evg.photosharing.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final UserService userService;

    public RecommendationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public List<RecommendationDTO> getRecommendationUser() {
        Random rand = new Random();
        List<User> all = userService.getAll();
        if (all.size() > 5) {
            int i = rand.nextInt(all.size() - 5);
            return all
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
        }
        return all.stream()
                .map(user -> {
                    RecommendationDTO recommendationDTO = new RecommendationDTO();
                    recommendationDTO.setUserId(user.getId());
                    recommendationDTO.setUsername(user.getUsername());
                    recommendationDTO.setImgURL("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
                    return recommendationDTO;
                })
                .collect(Collectors.toList());
    }

}
