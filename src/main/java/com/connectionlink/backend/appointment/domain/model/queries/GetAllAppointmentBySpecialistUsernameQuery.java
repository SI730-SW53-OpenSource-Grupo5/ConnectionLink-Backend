package com.connectionlink.backend.appointment.domain.model.queries;

public record GetAllAppointmentBySpecialistUsernameQuery(String username) {

    public GetAllAppointmentBySpecialistUsernameQuery {
        if (username == null) {
            throw new IllegalArgumentException("Specialist Username must not be null");
        }
    }
}
