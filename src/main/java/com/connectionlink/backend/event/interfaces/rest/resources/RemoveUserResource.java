package com.connectionlink.backend.event.interfaces.rest.resources;

public record RemoveUserResource(String username, Long eventId) {
    public RemoveUserResource {
        if (username == null) {
            throw new IllegalArgumentException("username cannot be null");
        }
        if (eventId == null ) {
            throw new IllegalArgumentException("Event Id cannot be null");
        }
    }
}