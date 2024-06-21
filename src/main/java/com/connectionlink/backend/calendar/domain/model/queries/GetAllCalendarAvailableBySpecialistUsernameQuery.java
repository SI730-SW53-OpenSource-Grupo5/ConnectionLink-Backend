package com.connectionlink.backend.calendar.domain.model.queries;

public record GetAllCalendarAvailableBySpecialistUsernameQuery(String specialistUsername) {
    public GetAllCalendarAvailableBySpecialistUsernameQuery {
        if(specialistUsername == null || specialistUsername.isBlank()) {
            throw  new IllegalArgumentException("specialist Username cannot be null or empty");
        }
    }
}
