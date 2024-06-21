package com.connectionlink.backend.review.domain.model.queries;

public record GetAllReviewBySpecialistUsernameQuery(String username) {
    public GetAllReviewBySpecialistUsernameQuery {
        if(username == null || username.isBlank()) {
            throw  new IllegalArgumentException("Specialist username cannot be null");
        }
    }
}
