package com.connectionlink.backend.calendar.interfaces.rest;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarsBySpecialistUsernameQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllCalendarsQuery;
import com.connectionlink.backend.calendar.domain.services.CalendarCommandService;
import com.connectionlink.backend.calendar.domain.services.CalendarQueryService;
import com.connectionlink.backend.calendar.interfaces.rest.resources.CalendarResource;
import com.connectionlink.backend.calendar.interfaces.rest.resources.CreateCalendarResource;
import com.connectionlink.backend.calendar.interfaces.rest.transform.CalendarResourceFromEntityAssembler;
import com.connectionlink.backend.calendar.interfaces.rest.transform.CreateCalendarCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/calendars")
public class CalendarController {

    private final CalendarCommandService calendarCommandService;
    private final CalendarQueryService calendarQueryService;

    public CalendarController(CalendarCommandService calendarCommandService, CalendarQueryService calendarQueryService) {
        this.calendarCommandService = calendarCommandService;
        this.calendarQueryService = calendarQueryService;
    }

    @PostMapping
    public ResponseEntity<CalendarResource> createEvent(@RequestBody CreateCalendarResource resource) {
        Optional<Calendar> calendar = calendarCommandService.handle(CreateCalendarCommandFromResourceAssembler.toCommandFromResource(resource));
        return calendar.map(source -> ResponseEntity.ok(CalendarResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("")
    public ResponseEntity<List<CalendarResource>> getAllCalendars() {
        List<Calendar> calendars = calendarQueryService.handle(new GetAllCalendarsQuery());

        if(calendars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<CalendarResource> calendarResources = calendars.stream().map(CalendarResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(calendarResources);
    }

    @GetMapping("/user/username/{username}")
    public ResponseEntity<List<CalendarResource>> getAllCalendarBySpecialistUsername(@PathVariable String username) {
        List<Calendar> calendars = calendarQueryService.handle(new GetAllCalendarsBySpecialistUsernameQuery(username));

        if(calendars.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<CalendarResource> calendarResources = calendars.stream().map(CalendarResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(calendarResources);
    }
}
