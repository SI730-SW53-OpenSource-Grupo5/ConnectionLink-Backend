package com.connectionlink.backend.appointment.application.internal.commandservices;

import com.connectionlink.backend.appointment.domain.model.aggregates.Appointment;
import com.connectionlink.backend.appointment.domain.model.commands.CreateAppointmentCommand;
import com.connectionlink.backend.appointment.domain.model.commands.DeleteAppointmentCommand;
import com.connectionlink.backend.appointment.domain.model.commands.UpdateAppointmentCommand;
import com.connectionlink.backend.appointment.domain.services.AppointmentCommandService;
import com.connectionlink.backend.appointment.infraestructure.persistence.jpa.AppointmentRepository;
import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.infrastructure.persistence.jpa.CalendarRepository;
import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.connectionlink.backend.user.infrastructure.persitence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {

    private final AppointmentRepository appointmentRepository;

    private final UserRepository userRepository;

    private final CalendarRepository calendarRepository;

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository, UserRepository userRepository, CalendarRepository calendarRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.calendarRepository = calendarRepository;
    }


    @Override
    public Optional<Appointment> handle(CreateAppointmentCommand command) {
        User user = this.userRepository.findByUsername(command.userUsername()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Calendar calendar = this.calendarRepository.findById(command.calendarId()).orElseThrow(() -> new IllegalArgumentException("Calendar not found"));

        if(!calendar.getIsAvailable()) {
            throw new IllegalArgumentException("Calendar is not available");
        } else {
            calendar.setIsAvailable(false);
        }

        Appointment appointment = new Appointment(command, user, calendar);

        var appointmentSaved = this.appointmentRepository.save(appointment);

        return Optional.of(appointmentSaved);
    }


    @Override
    public Optional<Appointment> handle(UpdateAppointmentCommand command, Long id) {
        Appointment appointmentExist = this.appointmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
        User user = this.userRepository.findByUsername(command.userName()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Calendar calendar = this.calendarRepository.findById(command.calendarId()).orElseThrow(() -> new IllegalArgumentException("Calendar not found"));

        appointmentExist.setUser(user);
        appointmentExist.setCalendar(calendar);

        var appointmentUpdated = this.appointmentRepository.save(appointmentExist);

        return Optional.of(appointmentUpdated);
    }

    @Override
    public Optional<Appointment> handle(DeleteAppointmentCommand command){
        Appointment appointmentExist = this.appointmentRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        this.appointmentRepository.delete(appointmentExist);

        return Optional.of(appointmentExist);
    }
}
