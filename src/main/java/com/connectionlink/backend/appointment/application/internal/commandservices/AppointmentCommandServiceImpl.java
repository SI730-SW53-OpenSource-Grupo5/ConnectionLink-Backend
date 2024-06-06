package com.connectionlink.backend.appointment.application.internal.commandservices;


import com.connectionlink.backend.appointment.domain.model.aggregates.Appointment;
import com.connectionlink.backend.appointment.domain.model.commands.CreateAppointmentCommand;
import com.connectionlink.backend.appointment.domain.model.commands.UpdateAppointmentCommand;
import com.connectionlink.backend.appointment.domain.services.AppointmentCommandService;
import com.connectionlink.backend.appointment.infraestructure.persitence.jpa.AppointmentRepository;
import com.connectionlink.backend.event.domain.model.aggregates.Event;
import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.connectionlink.backend.user.infrastructure.persitence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {

    private  final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    private final UserRepository specialistRepository;

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository, UserRepository specialistRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.specialistRepository = specialistRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Appointment> handle(CreateAppointmentCommand command) {
        User user = this.userRepository.findByUsername(command.usernameUser()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        User specialist = this.specialistRepository.findByUsername(command.usernameSpecialist()).orElseThrow(() -> new IllegalArgumentException("Specialist not found"));

        Appointment appointment = new Appointment(command, user, specialist);

        var appointmentSaved = this.appointmentRepository.save(appointment);
        return Optional.of(appointmentSaved);
    }

    @Override
    public Optional<Appointment> handle(UpdateAppointmentCommand command, Long id) {
        User user = this.userRepository.findByUsername(command.usernameUser()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        User specialist = this.userRepository.findByUsername(command.usernameSpecialist()).orElseThrow(() -> new IllegalArgumentException("Specialist not found"));

        Appointment appointment = this.appointmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        appointment.setSpecialist(specialist);
        appointment.setUser(user);

        appointment.setTitle(command.title());
        appointment.setDescription(command.description());
        appointment.setDay(command.day());

        var appointmentUpdated = this.appointmentRepository.save(appointment);

        return Optional.of(appointmentUpdated);
    }
}
