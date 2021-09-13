package com.example.simpleprojectungram.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TokenRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
