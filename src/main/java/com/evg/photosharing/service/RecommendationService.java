package com.evg.photosharing.service;

import com.evg.photosharing.model.dto.RecommendationDTO;

import java.util.List;

public interface RecommendationService {
    List<RecommendationDTO> getRecommendationUser();
}
