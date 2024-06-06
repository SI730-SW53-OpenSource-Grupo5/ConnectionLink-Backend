package com.connectionlink.backend.appointment.domain.model.queries;

public record GetAllAppointmentByUserUsernameQuery(String username) {

    public GetAllAppointmentByUserUsernameQuery {
        if (username == null) {
            throw new IllegalArgumentException("Username must not be null");
        }
    }
}
