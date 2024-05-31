package com.connectionlink.backend.event.domain.model.commands;

import java.util.Date;

public record CreateEventCommand(String title, String description, String profileImageUrl, String bannerImageUrl, Date day, Long categoryId, String username) {
    public CreateEventCommand {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
        if (profileImageUrl == null || profileImageUrl.isBlank()) {
            throw new IllegalArgumentException("profileImageUrl cannot be null or empty");
        }
        if (bannerImageUrl == null || bannerImageUrl.isBlank()) {
            throw new IllegalArgumentException("bannerImageUrl cannot be null or empty");
        }
        if (day == null ) {
            throw new IllegalArgumentException("day cannot be null");
        }
        if (categoryId == null ) {
            throw new IllegalArgumentException("categoryId cannot be null");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("profileImageUrl cannot be null or empty");
        }
    }
}
