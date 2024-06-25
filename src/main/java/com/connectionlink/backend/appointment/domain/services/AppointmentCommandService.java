package com.connectionlink.backend.appointment.domain.services;

import com.connectionlink.backend.appointment.domain.model.aggregates.Appointment;
import com.connectionlink.backend.appointment.domain.model.commands.CreateAppointmentCommand;
import com.connectionlink.backend.appointment.domain.model.commands.DeleteAppointmentCommand;
import com.connectionlink.backend.appointment.domain.model.commands.UpdateAppointmentCommand;

import java.util.Optional;

public interface AppointmentCommandService {

    Optional<Appointment> handle(CreateAppointmentCommand command);

    Optional<Appointment> handle(UpdateAppointmentCommand command, Long id);

    Optional<Appointment> handle(DeleteAppointmentCommand command);
}
