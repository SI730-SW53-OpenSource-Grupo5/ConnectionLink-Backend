package com.connectionlink.backend.authuser.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record FirstName(String firstName) {

    public FirstName {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }

        // Regular expression (regex) to validate first name
        if (!firstName.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Invalid last name");
        }
    }

    public String getFirstName() {
        return firstName;
    }

}
