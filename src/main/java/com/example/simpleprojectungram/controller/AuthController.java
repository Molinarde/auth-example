package com.example.simpleprojectungram.controller;

import com.example.simpleprojectungram.exception.NoEntityException;
import com.example.simpleprojectungram.exception.NotFoundTokenException;
import com.example.simpleprojectungram.security.payload.request.TokenRefreshRequest;
import com.example.simpleprojectungram.security.payload.request.SignupRequest;
import com.example.simpleprojectungram.security.payload.request.TokenRequest;
import com.example.simpleprojectungram.service.AuthService;
import com.example.simpleprojectungram.service.impl.AuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest tokenRefreshRequest) throws NotFoundTokenException {

        return new ResponseEntity<>(authService.refreshToken(tokenRefreshRequest), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> regUser(@RequestBody SignupRequest signupRequest) {

        return authService.signup(signupRequest) ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody TokenRequest tokenRequest) throws NoEntityException {

        return new ResponseEntity<>(authService.signin(tokenRequest), HttpStatus.OK);
    }


}
