package com.connectionlink.backend.appointment.application.internal.commandservices;

import com.connectionlink.backend.appointment.domain.model.aggregates.Appointment;
import com.connectionlink.backend.appointment.domain.model.commands.CreateAppointmentCommand;
import com.connectionlink.backend.appointment.domain.services.AppointmentCommandService;
import com.connectionlink.backend.appointment.infraestructure.persistence.jpa.AppointmentRepository;
import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.infraestructure.persistence.jpa.CalendarRepository;
import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.connectionlink.backend.user.infrastructure.persitence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {

    private final AppointmentRepository appointmentRepository;

    private final UserRepository specialistRepository;

    private final UserRepository userRepository;

    private final CalendarRepository calendarRepository;

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository, UserRepository specialistRepository, UserRepository userRepository, CalendarRepository calendarRepository) {
        this.appointmentRepository = appointmentRepository;
        this.specialistRepository = specialistRepository;
        this.userRepository = userRepository;
        this.calendarRepository = calendarRepository;
    }


    @Override
    public Optional<Appointment> handle(CreateAppointmentCommand command) {
        User specialist = this.specialistRepository.findByUsername(command.specialistUsername()).orElseThrow(() -> new IllegalArgumentException("Specialist not found"));
        User user = this.userRepository.findByUsername(command.userUsername()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Calendar calendar = this.calendarRepository.findById(command.calendarId()).orElseThrow(() -> new IllegalArgumentException("Calendar not found"));

        Appointment appointment = new Appointment(command, specialist, user, calendar);

        var appointmentSaved = this.appointmentRepository.save(appointment);

        return Optional.of(appointmentSaved);
    }
}
