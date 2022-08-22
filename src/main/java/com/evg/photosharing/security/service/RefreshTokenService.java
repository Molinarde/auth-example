package com.evg.photosharing.security.service;

import com.evg.photosharing.exception.EntityNotFoundException;
import com.evg.photosharing.exception.TokenRefreshException;
import com.evg.photosharing.model.User;
import com.evg.photosharing.repository.RefreshTokenRepository;
import com.evg.photosharing.security.RefreshToken;
import com.evg.photosharing.service.UserService;
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
    private final UserService userService;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserService userService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userService = userService;
    }

    public Optional<RefreshToken> findByToken(String token) throws EntityNotFoundException {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(String userId) throws EntityNotFoundException {
        RefreshToken refreshToken = new RefreshToken();
        User userById = userService.getById(userId).get();

        refreshToken.setUserId(userId);
        refreshToken.setUsername(userById.getUsername());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(jwtRefreshTokenExpiration));

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new TokenRefreshException(refreshToken.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return refreshToken;
    }
}
