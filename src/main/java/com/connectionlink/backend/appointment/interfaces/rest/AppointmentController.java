package com.connectionlink.backend.appointment.interfaces.rest;

import com.connectionlink.backend.appointment.domain.model.aggregates.Appointment;
import com.connectionlink.backend.appointment.domain.model.queries.*;
import com.connectionlink.backend.appointment.domain.services.AppointmentCommandService;
import com.connectionlink.backend.appointment.domain.services.AppointmentQueryService;
import com.connectionlink.backend.appointment.interfaces.rest.resources.AppointmentResource;
import com.connectionlink.backend.appointment.interfaces.rest.resources.CreateAppointmentResource;
import com.connectionlink.backend.appointment.interfaces.rest.resources.UpdateAppointmentResource;
import com.connectionlink.backend.appointment.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import com.connectionlink.backend.appointment.interfaces.rest.transform.CreateAppointmentCommandFromResourceAssembler;
import com.connectionlink.backend.appointment.interfaces.rest.transform.UpdateAppointmentCommandFromResourceAssembler;
import com.connectionlink.backend.event.domain.model.aggregates.Event;
import com.connectionlink.backend.event.interfaces.rest.resources.CreateEventResource;
import com.connectionlink.backend.event.interfaces.rest.resources.EventResource;
import com.connectionlink.backend.event.interfaces.rest.transform.CreateEventComamndFromResourceAssembler;
import com.connectionlink.backend.event.interfaces.rest.transform.EventResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResource> updateAppointment(@PathVariable Long id, @RequestBody UpdateAppointmentResource resource) {
        Optional<Appointment> appointment = appointmentCommandService.handle(UpdateAppointmentCommandFromResourceAssembler.toCommandFromResource(resource), id);
        return appointment.map(source -> ResponseEntity.ok(AppointmentResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("")
    public ResponseEntity<List<AppointmentResource>> getAllAppointment() {
        List<Appointment> appointments = appointmentQueryService.handle(new GetAllAppointmentQuery());

        if(appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<AppointmentResource> appointmentsResource = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(appointmentsResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResource> getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentQueryService.handle(new GetAppointmentByIdQuery(id));

        return appointment.map(source -> ResponseEntity.ok(AppointmentResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<AppointmentResource>> getAppointmentByUserId(@PathVariable Long id) {
        List<Appointment> appointments = appointmentQueryService.handle(new GetAllAppointmentByUserIdQuery(id));

        if(appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<AppointmentResource> appointmentsResource = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(appointmentsResource);
    }

    @GetMapping("/user/username/{username}")
    public ResponseEntity<List<AppointmentResource>> getAppointmentByUsername(@PathVariable String username) {
        List<Appointment> appointments = appointmentQueryService.handle(new GetAllAppointmentByUserUsernameQuery(username));

        if(appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<AppointmentResource> appointmentsResource = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(appointmentsResource);
    }


    @GetMapping("specialist/{id}")
    public ResponseEntity<List<AppointmentResource>> getAppointmentBySpecialistId(@PathVariable Long id) {
        List<Appointment> appointments = appointmentQueryService.handle(new GetAllAppointmentBySpecialistIdQuery(id));

        if(appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<AppointmentResource> appointmentsResource = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(appointmentsResource);
    }

    @GetMapping("specialist/username/{username}")
    public ResponseEntity<List<AppointmentResource>> getAppointmentBySpecialistUsername(@PathVariable String username) {
        List<Appointment> appointments = appointmentQueryService.handle(new GetAllAppointmentBySpecialistUsernameQuery(username));

        if(appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<AppointmentResource> appointmentsResource = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(appointmentsResource);
    }
}
