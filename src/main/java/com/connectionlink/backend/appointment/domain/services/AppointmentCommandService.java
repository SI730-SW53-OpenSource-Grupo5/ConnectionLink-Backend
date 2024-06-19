package com.connectionlink.backend.appointment.domain.services;

import com.connectionlink.backend.appointment.domain.model.aggregates.Appointment;
import com.connectionlink.backend.appointment.domain.model.commands.CreateAppointmentCommand;

import java.util.Optional;

public interface AppointmentCommandService {

    Optional<Appointment> handle(CreateAppointmentCommand command);
}
