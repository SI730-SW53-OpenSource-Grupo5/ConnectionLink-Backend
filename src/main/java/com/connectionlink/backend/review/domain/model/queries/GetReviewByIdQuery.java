package com.connectionlink.backend.review.domain.model.queries;

public record GetReviewByIdQuery(Long id) {
    public GetReviewByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("Review Id cannot be null");

        }
    }
}
