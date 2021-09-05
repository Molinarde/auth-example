package com.example.simpleprojectungram.service;

import com.example.simpleprojectungram.model.Users;
import com.example.simpleprojectungram.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users findUserById(String userId) {
        return userRepository.findById(userId).get();
    }

    public Users saveUser(Users user) {
        return userRepository.insert(user);
    }

    public List<Users> findAll() {
        return userRepository.findAll();
    }
}
