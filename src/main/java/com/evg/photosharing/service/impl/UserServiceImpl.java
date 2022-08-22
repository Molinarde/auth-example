package com.evg.photosharing.service.impl;

import com.evg.photosharing.exception.EntityNotFoundException;
import com.evg.photosharing.model.User;
import com.evg.photosharing.repository.UserRepository;
import com.evg.photosharing.security.payload.request.TokenRequest;
import com.evg.photosharing.service.UserService;
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
    public User addUser(User user) {
        return userRepository.insert(user);
    }

    @Override
    public Optional<User> getById(String userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User getByUsernameAndPassword(TokenRequest loginRequest) throws EntityNotFoundException {

        User userEntity = getByUsername(loginRequest.getUsername()).orElseThrow(() -> new EntityNotFoundException(String.format("No found %s", loginRequest.getUsername())));
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
