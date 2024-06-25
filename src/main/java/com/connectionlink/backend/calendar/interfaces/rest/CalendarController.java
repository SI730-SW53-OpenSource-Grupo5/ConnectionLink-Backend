package com.connectionlink.backend.calendar.interfaces.rest;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarAvailableBySpecialistUsernameQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarBySpecialistUsernameQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetCalendarByIdQuery;
import com.connectionlink.backend.calendar.domain.services.CalendarCommandService;
import com.connectionlink.backend.calendar.domain.services.CalendarQueryService;
import com.connectionlink.backend.calendar.interfaces.rest.resources.*;
import com.connectionlink.backend.calendar.interfaces.rest.transform.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/calendars")
public class CalendarController {
    private final CalendarCommandService calendarCommandService;
    private final CalendarQueryService calendarQueryService;

    public CalendarController(CalendarCommandService calendarCommandService, CalendarQueryService calendarQueryService) {
        this.calendarCommandService = calendarCommandService;
        this.calendarQueryService = calendarQueryService;
    }

    @GetMapping
    public ResponseEntity<List<CalendarResource>> getAllCalendar() {
        List<Calendar> calendars = this.calendarQueryService.handle(new GetAllCalendarQuery());

        if(calendars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<CalendarResource> calendarResources = calendars.stream().map(CalendarResourceFromEntityAssembler::toResourceFromEntity).toList();

        return ResponseEntity.ok(calendarResources);
    }

    @GetMapping("/specialist/{username}")
    public ResponseEntity<List<CalendarResource>> getAllCalendarBySpecialistUsername(@PathVariable String username) {
        List<Calendar> calendars = this.calendarQueryService.handle(new GetAllCalendarBySpecialistUsernameQuery(username));

        if(calendars.isEmpty()) {
            ResponseEntity.noContent().build();
        }

        List<CalendarResource> calendarResources = calendars.stream().map(CalendarResourceFromEntityAssembler::toResourceFromEntity).toList();

        return ResponseEntity.ok(calendarResources);
    }

    @GetMapping("/available/specialist/{username}")
    public ResponseEntity<List<CalendarResource>> getAllCalendarBySpecialistUsernameAndAvailableTrue(@PathVariable String username) {
        List<Calendar> calendars = this.calendarQueryService.handle(new GetAllCalendarAvailableBySpecialistUsernameQuery(username));

        if(calendars.isEmpty()) {
            ResponseEntity.noContent().build();
        }

        List<CalendarResource> calendarResources = calendars.stream().map(CalendarResourceFromEntityAssembler::toResourceFromEntity).toList();

        return ResponseEntity.ok(calendarResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalendarResource> getCalendarById(@PathVariable Long id) {
        Optional<Calendar> calendar = this.calendarQueryService.handle(new GetCalendarByIdQuery(id));

        if(calendar.isEmpty()) {
            ResponseEntity.noContent().build();
        }

        return calendar.map(source -> ResponseEntity.ok(CalendarResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<CalendarResource> createCalendar(@RequestBody CreateCalendarResource resource) {
        Optional<Calendar> calendar = this.calendarCommandService.handle(CreateCalendarCommandFromResourceAssembler.toCommandFromResource(resource));

        return calendar.map(source -> ResponseEntity.ok(CalendarResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalendarResource> updateCalendar(@RequestBody UpdateCalendarResource resource, @PathVariable Long id) {
        Optional<Calendar> calendar = this.calendarCommandService.handle(UpdateCalendarCommandFromResourceAssembler.toCommandFromResource(resource), id);

        return calendar.map(source -> ResponseEntity.ok(CalendarResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/available/{id}")
    public ResponseEntity<CalendarResource> updateCalendarAvailable(@RequestBody UpdateCalendarAvailableResource resource, @PathVariable Long id) {
        Optional<Calendar> calendar = this.calendarCommandService.handle(UpdateCalendarAvailableCommandFromResourceAssembler.toCommandFromResource(resource), id);

        return calendar.map(source -> ResponseEntity.ok(CalendarResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CalendarResource> deleteCalendar(@PathVariable Long id) {
        Optional<Calendar> calendar = this.calendarCommandService.handle(DeleteCalendarCommandFromResourceAssembler.toCommandFromResource(new DeleteCalendarResource(id)));

        return calendar.map(source -> ResponseEntity.ok(CalendarResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
