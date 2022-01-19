package com.example.simpleprojectungram.service;

import com.example.simpleprojectungram.model.dto.RecommendationDTO;

import java.util.List;

public interface RecommendationService {
    List<RecommendationDTO> getRecommendationUser();
}
