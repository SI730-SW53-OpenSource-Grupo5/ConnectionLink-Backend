package com.connectionlink.backend.calendar.domain.model.queries;

public record GetAllCalendarBySpecialistUsernameQuery(String specialistUsername) {
    public GetAllCalendarBySpecialistUsernameQuery {
        if(specialistUsername == null || specialistUsername.isBlank()) {
            throw  new IllegalArgumentException("specialist Username cannot be null or empty");
        }
    }
}
