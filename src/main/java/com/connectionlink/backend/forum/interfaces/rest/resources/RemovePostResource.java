package com.connectionlink.backend.forum.interfaces.rest.resources;

public record RemovePostResource(Long postId) {

    public RemovePostResource {
        if (postId == null) {
            throw new IllegalArgumentException("Post Id cannot be null");
        }
    }

}
