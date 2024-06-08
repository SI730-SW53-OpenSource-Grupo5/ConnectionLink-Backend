package com.connectionlink.backend.authuser.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Role(String role) {

    public Role {
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
    }

    public String getRole() {
        return role;
    }

}
