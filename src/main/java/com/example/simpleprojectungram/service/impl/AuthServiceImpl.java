package com.example.simpleprojectungram.service.impl;

import com.example.simpleprojectungram.exception.NoEntityException;
import com.example.simpleprojectungram.exception.NotFoundTokenException;
import com.example.simpleprojectungram.exception.TokenRefreshException;
import com.example.simpleprojectungram.security.RefreshToken;
import com.example.simpleprojectungram.model.Role;
import com.example.simpleprojectungram.model.User;
import com.example.simpleprojectungram.security.service.RefreshTokenService;
import com.example.simpleprojectungram.security.jwt.JwtUtil;
import com.example.simpleprojectungram.security.payload.request.SignupRequest;
import com.example.simpleprojectungram.security.payload.request.TokenRefreshRequest;
import com.example.simpleprojectungram.security.payload.request.TokenRequest;
import com.example.simpleprojectungram.security.payload.response.TokenRefreshResponse;
import com.example.simpleprojectungram.security.payload.response.TokenResponse;
import com.example.simpleprojectungram.service.AuthService;
import com.example.simpleprojectungram.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserServiceImpl userService, RefreshTokenService refreshTokenService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest) throws NotFoundTokenException {
        String refreshToken = tokenRefreshRequest.getRefreshToken();
       return refreshTokenService
                .findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUsername)
                .map(username -> {
                    String token = jwtUtil.generateToken(username);
                    return new TokenRefreshResponse(token, refreshToken);
                }).orElseThrow(()-> new TokenRefreshException(refreshToken, "Refresh token is not in database!"));
    }

    @Override
    public boolean signup(SignupRequest signupRequest){
        Optional<User> byUsername = userService.getByUsername(signupRequest.getUsername());
        Optional<User> byEmail = userService.getByUsername(signupRequest.getEmail());

        if(byUsername.isPresent() || byEmail.isPresent()){
            return false;
        }

        User users = new User();
        users.setUsername(signupRequest.getUsername());
        users.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        users.setEmail(signupRequest.getEmail());
        Set<Role> role = new HashSet<>();
        role.add(Role.ROLE_USER);
        users.setRole(role);

        userService.insertUser(users);
        return true;
    }

    @Override
    public TokenResponse signin(TokenRequest tokenRequest) throws NoEntityException {

        User users = userService.getByUsernameAndPassword(tokenRequest);

        String accessToken = jwtUtil.generateToken(users.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(users.getId());

        return new TokenResponse(
                accessToken,
                refreshToken.getToken(),
                users.getId(),
                users.getEmail(),
                users.getUsername(),
                users.getRole());
    }
}
