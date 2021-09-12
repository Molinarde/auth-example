package com.example.simpleprojectungram.exception;

public class NotFoundTokenException extends Exception {
    public NotFoundTokenException(String token_not_found) {
        super(token_not_found);
    }
}
