package com.example.simpleprojectungram.service;

import com.example.simpleprojectungram.exception.NoEntityException;
import com.example.simpleprojectungram.exception.NotFoundTokenException;
import com.example.simpleprojectungram.security.payload.request.SignupRequest;
import com.example.simpleprojectungram.security.payload.request.TokenRefreshRequest;
import com.example.simpleprojectungram.security.payload.request.TokenRequest;
import com.example.simpleprojectungram.security.payload.response.TokenRefreshResponse;
import com.example.simpleprojectungram.security.payload.response.TokenResponse;

public interface AuthService {
    TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest) throws NotFoundTokenException;
    boolean signup(SignupRequest signupRequest);
    TokenResponse signin(TokenRequest tokenRequest) throws NoEntityException;
}
