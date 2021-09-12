package com.example.simpleprojectungram.service;

import com.example.simpleprojectungram.exception.NoEntityException;
import com.example.simpleprojectungram.exception.NotFoundTokenException;
import com.example.simpleprojectungram.exception.TokenRefreshException;
import com.example.simpleprojectungram.model.RefreshToken;
import com.example.simpleprojectungram.model.Role;
import com.example.simpleprojectungram.model.Users;
import com.example.simpleprojectungram.model.dto.*;
import com.example.simpleprojectungram.security.jwt.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserService userService, RefreshTokenService refreshTokenService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

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

    public boolean signup(SignupRequest signupRequest){
        Optional<Users> byUsername = userService.findByUsername(signupRequest.getUsername());
        Optional<Users> byEmail = userService.findByUsername(signupRequest.getEmail());

        if(byUsername.isPresent() || byEmail.isPresent()){
            return false;
        }

        Users users = new Users();
        users.setUsername(signupRequest.getUsername());
        users.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        users.setEmail(signupRequest.getEmail());
        Set<Role> role = new HashSet<>();
        role.add(Role.ROLE_USER);
        users.setRole(role);

        userService.insertUser(users);
        return true;
    }

    public TokenResponse signin(TokenRequest tokenRequest) throws NoEntityException {

        Users users = userService.findByUsernameAndPassword(tokenRequest);

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
