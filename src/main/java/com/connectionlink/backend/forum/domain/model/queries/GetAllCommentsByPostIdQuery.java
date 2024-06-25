package com.connectionlink.backend.forum.domain.model.queries;

public record GetAllCommentsByPostIdQuery(Long postId) {

    public GetAllCommentsByPostIdQuery {
        if(postId == null) {
            throw new IllegalArgumentException("Post id cannot be null");
        }
    }

}
