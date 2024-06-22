package com.connectionlink.backend.appointment.application.internal.queryservices;

import com.connectionlink.backend.appointment.domain.model.aggregates.Appointment;
import com.connectionlink.backend.appointment.domain.model.queries.*;
import com.connectionlink.backend.appointment.domain.services.AppointmentQueryService;
import com.connectionlink.backend.appointment.infraestructure.persistence.jpa.AppointmentRepository;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarBySpecialistUsernameQuery;
import com.connectionlink.backend.calendar.infrastructure.persistence.jpa.CalendarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {

    private final AppointmentRepository appointmentRepository;
    private final CalendarRepository calendarRepository;

    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository, CalendarRepository calendarRepository) {
        this.appointmentRepository = appointmentRepository;
        this.calendarRepository = calendarRepository;
    }

    @Override
    public Optional<Appointment> handle(GetAppointmentByIdQuery query) {
        return this.appointmentRepository.findAppointmentById(query.id());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentBySpecialistUsernameQuery query) {
       return this.appointmentRepository.findAllByCalendarSpecialistUsername(query.specialistUsername());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentByUserUsernameQuery query) {
        return this.appointmentRepository.findAllByUserUsername(query.username());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentByCalendarIdQuery query) {
        return this.appointmentRepository.findAllByCalendarId(query.id());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsQuery query) {
        return this.appointmentRepository.findAll();
    }
}
