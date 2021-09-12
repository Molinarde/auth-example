package com.example.simpleprojectungram.service;

import com.example.simpleprojectungram.exception.NoEntityException;
import com.example.simpleprojectungram.model.Users;
import com.example.simpleprojectungram.repository.UserRepository;
import com.example.simpleprojectungram.model.dto.TokenRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users findUserById(String userId) {
        return userRepository.findById(userId).get();
    }

    public Users insertUser(Users user) {
        return userRepository.insert(user);
    }

    public Optional<Users> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public Users findByUsernameAndPassword(TokenRequest loginRequest) throws NoEntityException {

        Users userEntity = findByUsername(loginRequest.getUsername()).orElseThrow(() -> new NoEntityException(String.format("No found %s", loginRequest.getUsername())));
        if (userEntity != null) {
            if (BCrypt.checkpw(loginRequest.getPassword(), userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    public List<Users> findAll() {
        return userRepository.findAll();
    }

    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
