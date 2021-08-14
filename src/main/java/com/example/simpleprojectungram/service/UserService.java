package com.example.simpleprojectungram.service;

import com.example.simpleprojectungram.model.User;
import com.example.simpleprojectungram.model.dto.RecommendationDTO;
import com.example.simpleprojectungram.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String userId) {
        return userRepository.findById(userId).get();
    }

    public User saveUser(User user) {
        return userRepository.insert(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
