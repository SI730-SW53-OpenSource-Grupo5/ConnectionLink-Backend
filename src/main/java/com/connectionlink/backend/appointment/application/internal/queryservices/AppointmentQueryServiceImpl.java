package com.connectionlink.backend.appointment.application.internal.queryservices;


import com.connectionlink.backend.appointment.domain.model.aggregates.Appointment;
import com.connectionlink.backend.appointment.domain.model.queries.*;
import com.connectionlink.backend.appointment.domain.services.AppointmentQueryService;
import com.connectionlink.backend.appointment.infraestructure.persitence.jpa.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Optional<Appointment> handle(GetAppointmentByIdQuery query) {
        return this.appointmentRepository.findById(query.id());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentBySpecialistIdQuery query) {
        return this.appointmentRepository.findBySpecialistId(query.id());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentBySpecialistUsernameQuery query) {
        return this.appointmentRepository.findBySpecialistUsername(query.username());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentByUserIdQuery query) {
        return this.appointmentRepository.findByUserId(query.id());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentByUserUsernameQuery query) {
        return this.appointmentRepository.findByUserUsername(query.username());
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentQuery query) {
        return this.appointmentRepository.findAll();
    }

}
