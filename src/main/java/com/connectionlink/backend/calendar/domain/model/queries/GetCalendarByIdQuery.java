package com.connectionlink.backend.calendar.domain.model.queries;

public record GetCalendarByIdQuery(Long id) {
    public GetCalendarByIdQuery {
        if(id == null) {
            throw  new IllegalArgumentException("id cannot be null");
        }
    }
}
