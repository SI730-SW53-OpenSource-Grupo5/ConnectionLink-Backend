package com.connectionlink.backend.authuser.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Phone(String phone) {

    public Phone {
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone cannot be null or empty");
        }

        // Regular expression (regex) to validate phone number
        if (!phone.matches("^\\+(?:[0-9] ?){6,14}[0-9]$")) {
            throw new IllegalArgumentException("Phone is invalid");
        }
    }

    public String getPhone() {
        return phone;
    }

}
