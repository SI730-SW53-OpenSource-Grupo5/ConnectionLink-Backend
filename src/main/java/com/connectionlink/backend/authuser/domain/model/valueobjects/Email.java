package com.connectionlink.backend.authuser.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Email(String email) {

    public Email {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        // Regular expression (regex) to validate email address
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new IllegalArgumentException("Email is invalid");
        }
    }

    public String getEmail() {
        return email;
    }
}
