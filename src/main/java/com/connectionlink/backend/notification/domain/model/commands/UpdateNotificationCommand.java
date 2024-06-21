package com.connectionlink.backend.notification.domain.model.commands;

public record UpdateNotificationCommand(Boolean isRead) {
    public UpdateNotificationCommand {
        if (isRead == null) {
            throw new IllegalArgumentException("isRead  cannot be null");
        }
    }
}
