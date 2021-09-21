package com.example.simpleprojectungram.service;

import com.example.simpleprojectungram.exception.NoEntityException;
import com.example.simpleprojectungram.model.User;
import com.example.simpleprojectungram.security.payload.request.TokenRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User insertUser(User user);
    User getById(String id);
    User getByUsernameAndPassword(TokenRequest loginRequest) throws NoEntityException;
    Optional<User> getByUsername(String username);
    List<User> getAll();
}
