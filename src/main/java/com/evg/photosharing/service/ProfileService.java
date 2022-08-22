package com.evg.photosharing.service;

import com.evg.photosharing.model.dto.ProfileDTO;

import java.util.Optional;

public interface ProfileService {
    Optional<ProfileDTO> getProfileById(String userId);
}
