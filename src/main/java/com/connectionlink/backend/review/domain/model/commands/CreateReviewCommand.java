package com.connectionlink.backend.review.domain.model.commands;

import com.connectionlink.backend.user.domain.model.commands.CreateUserCommand;

public record CreateReviewCommand(String description, String specialistUsername, String userUsername) {
    public CreateReviewCommand {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (specialistUsername == null || specialistUsername.isBlank()) {
            throw new IllegalArgumentException("Specialist Username cannot be null or empty");
        }
        if (userUsername == null || userUsername.isBlank()) {
            throw new IllegalArgumentException("User Username cannot be null or empty");
        }

    }
}
