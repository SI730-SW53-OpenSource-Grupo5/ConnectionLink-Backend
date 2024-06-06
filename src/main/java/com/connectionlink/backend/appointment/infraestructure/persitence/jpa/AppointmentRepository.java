package com.connectionlink.backend.appointment.infraestructure.persitence.jpa;

import com.connectionlink.backend.appointment.domain.model.aggregates.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAll();

    List<Appointment> findByUserId(Long userId);

    List<Appointment> findByUserUsername(String username);

    List<Appointment> findBySpecialistId(Long specialistId);

    List<Appointment> findBySpecialistUsername(String username);

    Optional<Appointment> findById(Long id);

}
