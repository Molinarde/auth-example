package com.evg.photosharing.service;

import com.evg.photosharing.model.User;
import com.evg.photosharing.exception.EntityNotFoundException;
import com.evg.photosharing.security.payload.request.TokenRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User addUser(User user);

    User getByUsernameAndPassword(TokenRequest loginRequest) throws EntityNotFoundException;

    Optional<User> getById(String id);

    Optional<User> getByUsername(String username);

    List<User> getAll();
}
