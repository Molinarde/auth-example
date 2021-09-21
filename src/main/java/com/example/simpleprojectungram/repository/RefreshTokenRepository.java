package com.example.simpleprojectungram.repository;

import com.example.simpleprojectungram.security.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    @Override
    Optional<RefreshToken> findById(String s);

    Optional<RefreshToken> findByToken(String token);
}
