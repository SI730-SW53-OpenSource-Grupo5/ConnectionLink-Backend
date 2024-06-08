package com.connectionlink.backend.authuser.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record LastName(String lastName) {

    public LastName {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }

        // Regular expression (regex) to validate last name
        if (!lastName.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Invalid last name");
        }
    }

    public String getLastName() {
        return lastName;
    }

}
