package com.connectionlink.backend.forum.domain.model.commands;

import java.util.Date;

public record CreatePostCommand(String title, String content, Integer likes, Date createdAt, Long userId) {

    public CreatePostCommand {
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
