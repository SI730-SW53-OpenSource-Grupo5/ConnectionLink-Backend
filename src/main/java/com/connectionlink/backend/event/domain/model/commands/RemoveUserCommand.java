package com.connectionlink.backend.event.domain.model.commands;

public record RemoveUserCommand(String username, Long eventId) {
    public RemoveUserCommand {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username cannot be null or empty");
        }
        if (eventId == null ) {
            throw new IllegalArgumentException("Event Id cannot be null");
        }
    }
}