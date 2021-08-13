package com.example.simpleprojectungram.controller;

import com.example.simpleprojectungram.model.User;
import com.example.simpleprojectungram.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user){
        User saveUser = userService.saveUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.OK);
    }
}
