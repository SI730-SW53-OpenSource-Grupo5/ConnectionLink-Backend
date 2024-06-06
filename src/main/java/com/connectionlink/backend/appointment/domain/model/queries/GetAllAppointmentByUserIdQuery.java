package com.connectionlink.backend.appointment.domain.model.queries;

public record GetAllAppointmentByUserIdQuery(Long id) {

    public GetAllAppointmentByUserIdQuery {
        if(id == null) {
            throw  new IllegalArgumentException("User Id cannot be null");
        }
    }
}
