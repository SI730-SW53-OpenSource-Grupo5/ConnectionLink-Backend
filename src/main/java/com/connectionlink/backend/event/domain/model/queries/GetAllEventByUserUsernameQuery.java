package com.connectionlink.backend.event.domain.model.queries;

public record GetAllEventByUserUsernameQuery(String username) {
    public GetAllEventByUserUsernameQuery {
        if(username == null) {
            throw  new IllegalArgumentException("User username cannot be null");
        }
    }
}
