package com.connectionlink.backend.event.domain.model.queries;

public record GetAllEventByUserIdQuery(Long id) {
    public GetAllEventByUserIdQuery {
        if(id == null) {
            throw  new IllegalArgumentException("User Id cannot be null");
        }
    }
}
