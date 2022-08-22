package com.evg.photosharing.controller;

import com.evg.photosharing.exception.EntityNotFoundException;
import com.evg.photosharing.security.payload.request.SignupRequest;
import com.evg.photosharing.security.payload.request.TokenRefreshRequest;
import com.evg.photosharing.security.payload.request.TokenRequest;
import com.evg.photosharing.service.AuthService;
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
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest tokenRefreshRequest) throws EntityNotFoundException {

        return new ResponseEntity<>(authService.refreshToken(tokenRefreshRequest), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> regUser(@RequestBody SignupRequest signupRequest) {

        return authService.signup(signupRequest) ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody TokenRequest tokenRequest) throws EntityNotFoundException {

        return new ResponseEntity<>(authService.signin(tokenRequest), HttpStatus.OK);
    }


}
