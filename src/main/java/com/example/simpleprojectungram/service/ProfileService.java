package com.example.simpleprojectungram.service;

import com.example.simpleprojectungram.model.dto.ProfileDTO;

import java.util.Optional;

public interface ProfileService {
    Optional<ProfileDTO> getProfileById(String userId);
}
