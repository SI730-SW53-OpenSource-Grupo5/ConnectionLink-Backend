package com.connectionlink.backend.forum.interfaces.rest.resources;

public record GetPostCommentsResource(Long postId) {
    public GetPostCommentsResource {
        if (postId == null) {
            throw new IllegalArgumentException("Post Id cannot be null");
        }
    }
}