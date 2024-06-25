package com.connectionlink.backend.notification.domain.model.queries;

public record GetAllNotificationByUsernameQuery(String userUsername) {
    public GetAllNotificationByUsernameQuery {
        if (userUsername == null || userUsername.isBlank()) {
            throw new IllegalArgumentException("User Username  cannot be null or empty");
        }
    }
}
