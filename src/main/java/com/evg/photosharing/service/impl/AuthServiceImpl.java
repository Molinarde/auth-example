package com.evg.photosharing.service.impl;

import com.evg.photosharing.exception.EntityNotFoundException;
import com.evg.photosharing.model.Role;
import com.evg.photosharing.model.User;
import com.evg.photosharing.security.RefreshToken;
import com.evg.photosharing.security.jwt.JwtUtil;
import com.evg.photosharing.security.payload.request.SignupRequest;
import com.evg.photosharing.security.payload.request.TokenRefreshRequest;
import com.evg.photosharing.security.payload.request.TokenRequest;
import com.evg.photosharing.security.payload.response.TokenRefreshResponse;
import com.evg.photosharing.security.payload.response.TokenResponse;
import com.evg.photosharing.security.service.RefreshTokenService;
import com.evg.photosharing.service.AuthService;
import com.evg.photosharing.service.UserService;
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

    public AuthServiceImpl(UserService userService, RefreshTokenService refreshTokenService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest) throws EntityNotFoundException {
        String refreshToken = tokenRefreshRequest.getRefreshToken();
        return refreshTokenService
                .findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUsername)
                .map(username -> {
                    String token = jwtUtil.generateToken(username);
                    return new TokenRefreshResponse(token, refreshToken);
                }).orElseThrow(() -> new EntityNotFoundException("Refresh token is not in database!"));
    }

    @Override
    public boolean signup(SignupRequest signupRequest) {
        Optional<User> byUsername = userService.getByUsername(signupRequest.getUsername());
        Optional<User> byEmail = userService.getByUsername(signupRequest.getEmail());

        if (byUsername.isPresent() || byEmail.isPresent()) {
            return false;
        }

        User users = new User();
        users.setUsername(signupRequest.getUsername());
        users.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        users.setEmail(signupRequest.getEmail());
        Set<Role> role = new HashSet<>();
        role.add(Role.ROLE_USER);
        users.setRole(role);

        userService.addUser(users);
        return true;
    }

    @Override
    public TokenResponse signin(TokenRequest tokenRequest) throws EntityNotFoundException {

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
