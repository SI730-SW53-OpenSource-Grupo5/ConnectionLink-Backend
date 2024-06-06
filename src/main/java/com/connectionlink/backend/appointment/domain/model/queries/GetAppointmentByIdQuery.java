package com.connectionlink.backend.appointment.domain.model.queries;

public record GetAppointmentByIdQuery(Long id) {

    public GetAppointmentByIdQuery {
        if(id == null) {
            throw  new IllegalArgumentException("Appointment Id cannot be null");
        }
    }
}
