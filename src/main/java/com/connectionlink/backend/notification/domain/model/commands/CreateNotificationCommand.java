package com.connectionlink.backend.notification.domain.model.commands;

public record CreateNotificationCommand(String title, String description, String url, String userUsername) {
    public CreateNotificationCommand {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description  cannot be null  or empty");
        }
        if (userUsername == null || userUsername.isBlank()) {
            throw new IllegalArgumentException("User Username  cannot be null  or empty");
        }
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("Url  cannot be null  or empty");
        }
    }
}
