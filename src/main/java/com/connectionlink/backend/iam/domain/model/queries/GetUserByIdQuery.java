package com.connectionlink.backend.iam.domain.model.queries;

public record GetUserByIdQuery(Long id) {
    public GetUserByIdQuery {
        if(id == null) {
            throw  new IllegalArgumentException("User Id cannot be null");
        }
    }
}
