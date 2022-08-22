package com.evg.photosharing.security;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document
public class RefreshToken {
    @Id
    private String id;
    private String token;
    private String username;
    private String userId;
    private Instant expiryDate;
}
