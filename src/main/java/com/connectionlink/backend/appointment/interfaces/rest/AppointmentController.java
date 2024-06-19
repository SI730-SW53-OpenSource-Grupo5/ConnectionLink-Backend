package com.connectionlink.backend.appointment.interfaces.rest;


import com.connectionlink.backend.appointment.domain.model.aggregates.Appointment;
import com.connectionlink.backend.appointment.domain.services.AppointmentCommandService;
import com.connectionlink.backend.appointment.domain.services.AppointmentQueryService;
import com.connectionlink.backend.appointment.interfaces.rest.resources.AppointmentResource;
import com.connectionlink.backend.appointment.interfaces.rest.resources.CreateAppointmentResource;
import com.connectionlink.backend.appointment.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import com.connectionlink.backend.appointment.interfaces.rest.transform.CreateAppointmentCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/appointments")
public class AppointmentController {

    private final AppointmentCommandService appointmentCommandService;
    private final AppointmentQueryService appointmentQueryService;

    public AppointmentController(AppointmentCommandService appointmentCommandService, AppointmentQueryService appointmentQueryService) {
        this.appointmentCommandService = appointmentCommandService;
        this.appointmentQueryService = appointmentQueryService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource resource) {
        Optional<Appointment> appointment = appointmentCommandService.handle(CreateAppointmentCommandFromResourceAssembler.toCommandFromResource(resource));
        return appointment.map(source -> ResponseEntity.ok(AppointmentResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
