package com.connectionlink.backend.iam.domain.model.commands;

import java.util.Date;

public record UpdateUserCommand(String fullName, String description, String profileImageUrl, String bannerImageUrl, Integer age, Date birthday, String cvUrl) {
    public UpdateUserCommand {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("fullName cannot be null or empty");
        }
        if (description == null) {
            throw new IllegalArgumentException("description cannot be null");
        }
        if (profileImageUrl == null ) {
            throw new IllegalArgumentException("profileImageUrl cannot be null");
        }
        if (bannerImageUrl == null) {
            throw new IllegalArgumentException("bannerImageUrl cannot be null");
        }
        if (age == null || age <= 0) {
            throw new IllegalArgumentException("age must be a positive integer");
        }
        if (birthday == null) {
            throw new IllegalArgumentException("birthday cannot be null");
        }
        if (cvUrl == null ) {
            throw new IllegalArgumentException("cvUrl cannot be null");
        }
    }
}
