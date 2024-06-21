package com.connectionlink.backend.calendar.domain.model.queries;

public record GetDayByIdQuery(Long id) {
    public GetDayByIdQuery {
        if(id == null) {
            throw  new IllegalArgumentException("id cannot be null");
        }
    }
}
