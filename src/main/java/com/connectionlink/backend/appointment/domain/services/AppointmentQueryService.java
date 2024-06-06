package com.connectionlink.backend.appointment.domain.services;

import com.connectionlink.backend.appointment.domain.model.aggregates.Appointment;
import com.connectionlink.backend.appointment.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface AppointmentQueryService {
    Optional<Appointment> handle(GetAppointmentByIdQuery query);

    List<Appointment> handle(GetAllAppointmentByUserIdQuery query);

    List<Appointment> handle(GetAllAppointmentByUserUsernameQuery query);

    List<Appointment> handle(GetAllAppointmentBySpecialistIdQuery query);

    List<Appointment> handle(GetAllAppointmentBySpecialistUsernameQuery query);

    List<Appointment> handle(GetAllAppointmentQuery query);
}
