package com.connectionlink.backend.event.domain.model.queries;

public record GetEventByIdQuery(Long id) {
    public GetEventByIdQuery {
        if(id == null) {
            throw  new IllegalArgumentException("Event Id cannot be null");
        }
    }
}
