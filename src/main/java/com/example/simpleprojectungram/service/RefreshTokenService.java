package com.example.simpleprojectungram.service;

import com.example.simpleprojectungram.exception.NotFoundTokenException;
import com.example.simpleprojectungram.exception.TokenRefreshException;
import com.example.simpleprojectungram.model.RefreshToken;
import com.example.simpleprojectungram.model.Users;
import com.example.simpleprojectungram.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${jwt.refreshTokenExpirationMs}")
    private long jwtRefreshTokenExpiration;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserService userService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userService = userService;
    }

    public Optional<RefreshToken> findByToken(String token) throws NotFoundTokenException {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(String userId){
        RefreshToken refreshToken = new RefreshToken();
        Users userById = userService.findUserById(userId);

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
