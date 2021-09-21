package com.example.simpleprojectungram.security.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TokenRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
