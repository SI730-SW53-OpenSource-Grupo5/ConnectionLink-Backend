package com.connectionlink.backend.appointment.infraestructure.persistence.jpa;

import com.connectionlink.backend.appointment.domain.model.aggregates.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAll();

    Optional<Appointment> findAppointmentById(Long id);

    List<Appointment> findAllByCalendarSpecialistUsername(String specialistUsername);

    List<Appointment> findAllByUserUsername(String userUsername);

    List<Appointment> findAllByCalendarId(Long calendarId);
}
