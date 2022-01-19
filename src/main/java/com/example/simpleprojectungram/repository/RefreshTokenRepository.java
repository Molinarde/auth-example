package com.example.simpleprojectungram.repository;

import com.example.simpleprojectungram.security.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    @Override
    Optional<RefreshToken> findById(String s);

    Optional<RefreshToken> findByToken(String token);
}
