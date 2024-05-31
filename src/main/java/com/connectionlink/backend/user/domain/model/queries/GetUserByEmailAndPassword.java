package com.connectionlink.backend.user.domain.model.queries;

public record GetUserByEmailAndPassword(String email, String password) {
    public GetUserByEmailAndPassword {
        if(email == null || email.isBlank()) {
            throw  new IllegalArgumentException("email cannot be null or empty");
        }
        if(password == null || password.isBlank()) {
            throw  new IllegalArgumentException("password cannot be null or empty");
        }
    }
}
