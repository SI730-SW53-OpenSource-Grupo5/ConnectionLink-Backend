package com.connectionlink.backend.event.interfaces.rest.resources;

public record AddUserResource(String username, Long eventId) {
    public AddUserResource {
        if (username == null) {
            throw new IllegalArgumentException("username cannot be null");
        }
        if (eventId == null ) {
            throw new IllegalArgumentException("Event Id cannot be null");
        }
    }
}