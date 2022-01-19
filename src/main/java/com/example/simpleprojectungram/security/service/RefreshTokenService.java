package com.example.simpleprojectungram.security.service;

import com.example.simpleprojectungram.exception.NoEntityException;
import com.example.simpleprojectungram.exception.NotFoundTokenException;
import com.example.simpleprojectungram.exception.TokenRefreshException;
import com.example.simpleprojectungram.security.RefreshToken;
import com.example.simpleprojectungram.model.User;
import com.example.simpleprojectungram.repository.RefreshTokenRepository;
import com.example.simpleprojectungram.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${jwt.refreshTokenExpirationMs}")
    private long jwtRefreshTokenExpiration;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserServiceImpl userService;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserServiceImpl userService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userService = userService;
    }

    public Optional<RefreshToken> findByToken(String token) throws NotFoundTokenException {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(String userId) throws NoEntityException {
        RefreshToken refreshToken = new RefreshToken();
        User userById = userService.getById(userId).get();

        refreshToken.setUserId(userId);
        refreshToken.setUsername(userById.getUsername());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(jwtRefreshTokenExpiration));

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken){
        if(refreshToken.getExpiryDate().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(refreshToken);
            throw new TokenRefreshException(refreshToken.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return refreshToken;
    }
}
