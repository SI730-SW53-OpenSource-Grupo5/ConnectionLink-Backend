package com.connectionlink.backend.forum.interfaces.rest.resources;

import java.util.Date;

public record CreatePostResource(String title, String content, Integer likes, Date createdAt, Long userId) {

    public CreatePostResource {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("content cannot be null or empty");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt cannot be null");
        }
        if (likes == null) {
            throw new IllegalArgumentException("likes cannot be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User Id cannot be null");
        }

    }
}
