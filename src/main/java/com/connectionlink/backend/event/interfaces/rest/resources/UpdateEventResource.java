package com.connectionlink.backend.event.interfaces.rest.resources;

import java.util.Date;

public record UpdateEventResource(Long id, String title, String description, String profileImageUrl, String bannerImageUrl, Date day, Long categoryId, String username) {
    public UpdateEventResource {
        if (title == null) {
            throw new IllegalArgumentException("title cannot be null");
        }
        if (description == null ) {
            throw new IllegalArgumentException("description cannot be null");
        }
        if (profileImageUrl == null) {
            throw new IllegalArgumentException("profileImageUrl cannot be null ");
        }
        if (bannerImageUrl == null) {
            throw new IllegalArgumentException("bannerImageUrl cannot be null");
        }
        if (day == null) {
            throw new IllegalArgumentException("day cannot be null");
        }
        if (categoryId == null) {
            throw new IllegalArgumentException("categoryId cannot be null");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username cannot be null or empty");
        }
    }
}