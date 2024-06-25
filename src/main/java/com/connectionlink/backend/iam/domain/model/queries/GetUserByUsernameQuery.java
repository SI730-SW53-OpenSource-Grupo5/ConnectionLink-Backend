package com.connectionlink.backend.iam.domain.model.queries;

public record GetUserByUsernameQuery(String username) {
    public GetUserByUsernameQuery {
        if(username == null || username.isBlank()) {
            throw  new IllegalArgumentException("User username cannot be null or empty");
        }
    }
}
