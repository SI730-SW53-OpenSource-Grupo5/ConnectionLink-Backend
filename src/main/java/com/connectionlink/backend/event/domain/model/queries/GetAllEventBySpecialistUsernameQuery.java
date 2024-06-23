package com.connectionlink.backend.event.domain.model.queries;

public record GetAllEventBySpecialistUsernameQuery(String username) {
    public GetAllEventBySpecialistUsernameQuery {
        if(username == null) {
            throw  new IllegalArgumentException("Specialist username cannot be null");
        }
    }
}
