package com.connectionlink.backend.appointment.interfaces.rest;


import com.connectionlink.backend.appointment.domain.model.aggregates.Appointment;
import com.connectionlink.backend.appointment.domain.model.commands.DeleteAppointmentCommand;
import com.connectionlink.backend.appointment.domain.model.queries.*;
import com.connectionlink.backend.appointment.domain.services.AppointmentCommandService;
import com.connectionlink.backend.appointment.domain.services.AppointmentQueryService;
import com.connectionlink.backend.appointment.interfaces.rest.resources.AppointmentResource;
import com.connectionlink.backend.appointment.interfaces.rest.resources.CreateAppointmentResource;
import com.connectionlink.backend.appointment.interfaces.rest.resources.DeleteAppointmentResource;
import com.connectionlink.backend.appointment.interfaces.rest.resources.UpdateAppointmentResource;
import com.connectionlink.backend.appointment.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import com.connectionlink.backend.appointment.interfaces.rest.transform.CreateAppointmentCommandFromResourceAssembler;
import com.connectionlink.backend.appointment.interfaces.rest.transform.DeleteAppointmentCommandFromResourceAssembler;
import com.connectionlink.backend.appointment.interfaces.rest.transform.UpdateAppointmentCommandFromResourceAssembler;
import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarBySpecialistUsernameQuery;
import com.connectionlink.backend.calendar.interfaces.rest.resources.CalendarResource;
import com.connectionlink.backend.calendar.interfaces.rest.resources.DeleteCalendarResource;
import com.connectionlink.backend.calendar.interfaces.rest.transform.CalendarResourceFromEntityAssembler;
import com.connectionlink.backend.calendar.interfaces.rest.transform.DeleteCalendarCommandFromResourceAssembler;
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
    public ResponseEntity<AppointmentResource> updateAppointment(@RequestBody UpdateAppointmentResource resource, @PathVariable Long id) {
        Optional<Appointment> appointment = appointmentCommandService.handle(UpdateAppointmentCommandFromResourceAssembler.toCommandFromResource(resource), id);
        return appointment.map(source -> ResponseEntity.ok(AppointmentResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppointmentResource> deleteAppointment(@PathVariable Long id) {
       Optional<Appointment> appointment = this.appointmentCommandService.handle(DeleteAppointmentCommandFromResourceAssembler.toCommandFromResource(new DeleteAppointmentResource(id)));
         return appointment.map(source -> ResponseEntity.ok(AppointmentResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResource>> getAllAppointment() {
        List<Appointment> appointments = this.appointmentQueryService.handle(new GetAllAppointmentsQuery());
        if(appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<AppointmentResource> appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(appointmentResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResource> getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> appointment = this.appointmentQueryService.handle(new GetAppointmentByIdQuery(id));
        return appointment.map(source -> ResponseEntity.ok(AppointmentResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<AppointmentResource>> getAllAppointmentByUserUsername(@PathVariable String username) {
        List<Appointment> appointments = this.appointmentQueryService.handle(new GetAllAppointmentByUserUsernameQuery(username));
        if(appointments.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        List<AppointmentResource> appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(appointmentResources);
    }

    @GetMapping("/calendar/{id}")
    public ResponseEntity<List<AppointmentResource>> getAllAppointmentByCalendarId(@PathVariable Long id) {
        List<Appointment> appointments = this.appointmentQueryService.handle(new GetAllAppointmentByCalendarIdQuery(id));
        if (appointments.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        List<AppointmentResource> appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(appointmentResources);
    }

    @GetMapping("/calendar/specialist/{username}")
    public ResponseEntity<List<AppointmentResource>> getAllAppointmentBySpecialistUsername(@PathVariable String username) {
        List<Appointment> appointments = this.appointmentQueryService.handle(new GetAllAppointmentBySpecialistUsernameQuery(username));
        if (appointments.isEmpty()) {
            ResponseEntity.noContent().build();
        }
        List<AppointmentResource> appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(appointmentResources);
    }
}
