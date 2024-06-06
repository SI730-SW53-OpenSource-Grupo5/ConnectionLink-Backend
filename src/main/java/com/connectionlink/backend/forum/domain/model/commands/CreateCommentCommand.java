package com.connectionlink.backend.forum.domain.model.commands;

import java.util.Date;

public record CreateCommentCommand(String content, Integer likes, Date createdAt, Long postId, Long userId) {

    public CreateCommentCommand {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("content cannot be null or empty");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt cannot be null");
        }
        if (likes == null) {
            throw new IllegalArgumentException("likes cannot be null");
        }
        if (postId == null) {
            throw new IllegalArgumentException("Post Id cannot be null");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User Id cannot be null");
        }
    }

}
