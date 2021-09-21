package com.example.simpleprojectungram.service.impl;

import com.example.simpleprojectungram.exception.NoEntityException;
import com.example.simpleprojectungram.model.User;
import com.example.simpleprojectungram.security.payload.request.TokenRequest;
import com.example.simpleprojectungram.repository.UserRepository;
import com.example.simpleprojectungram.service.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User insertUser(User user) {
        return userRepository.insert(user);
    }

    @Override
    public User getById(String userId) {
//        return userRepository.findById(userId).orElseThrow(() -> new NoEntityException(String.format("No found %s", userId)));
        return userRepository.findById(userId).get();
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User getByUsernameAndPassword(TokenRequest loginRequest) throws NoEntityException {

        User userEntity = getByUsername(loginRequest.getUsername()).orElseThrow(() -> new NoEntityException(String.format("No found %s", loginRequest.getUsername())));
        if (userEntity != null) {
            if (BCrypt.checkpw(loginRequest.getPassword(), userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
