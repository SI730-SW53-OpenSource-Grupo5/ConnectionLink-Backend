package com.connectionlink.backend.appointment.domain.model.commands;

public record DeleteAppointmentCommand(Long id) {

    public DeleteAppointmentCommand {
        if(id == null) {
            throw  new IllegalArgumentException("appointment id cannot be null");
        }
    }
}
