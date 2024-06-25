package com.connectionlink.backend.forum.domain.model.commands;

public record RemovePostCommand(Long postId) {

    public RemovePostCommand {
        if (postId == null) {
            throw new IllegalArgumentException("Post Id cannot be null");
        }
    }

}
