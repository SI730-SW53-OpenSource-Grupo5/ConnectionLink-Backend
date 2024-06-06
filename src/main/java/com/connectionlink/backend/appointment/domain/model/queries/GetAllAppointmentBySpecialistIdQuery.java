package com.connectionlink.backend.appointment.domain.model.queries;

public record GetAllAppointmentBySpecialistIdQuery(Long id) {

    public GetAllAppointmentBySpecialistIdQuery {
        if(id == null) {
            throw  new IllegalArgumentException("Specialist Id cannot be null");
        }
    }
}
