package com.connectionlink.backend.authuser.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Password(String password) {

    public Password {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        // if password length is less than 8 characters, throw an exception
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
    }

    public String getPassword() {
        return password;
    }
}
