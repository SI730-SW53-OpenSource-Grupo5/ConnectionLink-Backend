package com.connectionlink.backend.forum.domain.model.queries;

public record GetAllCommentsByUserIdQuery(Long userId) {

    public GetAllCommentsByUserIdQuery {
        if(userId == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }
    }
}
