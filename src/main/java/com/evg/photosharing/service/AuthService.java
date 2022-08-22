package com.evg.photosharing.service;

import com.evg.photosharing.exception.EntityNotFoundException;
import com.evg.photosharing.security.payload.request.SignupRequest;
import com.evg.photosharing.security.payload.request.TokenRefreshRequest;
import com.evg.photosharing.security.payload.request.TokenRequest;
import com.evg.photosharing.security.payload.response.TokenRefreshResponse;
import com.evg.photosharing.security.payload.response.TokenResponse;

public interface AuthService {
    TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest) throws EntityNotFoundException;

    boolean signup(SignupRequest signupRequest);

    TokenResponse signin(TokenRequest tokenRequest) throws EntityNotFoundException;
}
