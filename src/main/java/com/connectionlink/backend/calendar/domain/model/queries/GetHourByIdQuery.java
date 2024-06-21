package com.connectionlink.backend.calendar.domain.model.queries;

public record GetHourByIdQuery(Long id) {
    public GetHourByIdQuery {
        if(id == null) {
            throw  new IllegalArgumentException("id cannot be null");
        }
    }
}
