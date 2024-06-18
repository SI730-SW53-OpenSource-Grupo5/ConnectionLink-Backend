package com.connectionlink.backend.calendar.interfaces.rest;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.services.CalendarCommandService;
import com.connectionlink.backend.calendar.domain.services.CalendarQueryService;
import com.connectionlink.backend.calendar.interfaces.rest.resources.CalendarResource;
import com.connectionlink.backend.calendar.interfaces.rest.resources.CreateCalendarResource;
import com.connectionlink.backend.calendar.interfaces.rest.trasform.CalendarResourceFromEntityAssembler;
import com.connectionlink.backend.calendar.interfaces.rest.trasform.CreateCalendarCommandFromResourceAssembler;
import com.connectionlink.backend.category.interfaces.rest.resources.UpdateCategoryResource;
import com.connectionlink.backend.event.domain.model.aggregates.Event;
import com.connectionlink.backend.event.domain.model.queries.GetAllEventQuery;
import com.connectionlink.backend.event.interfaces.rest.resources.EventResource;
import com.connectionlink.backend.event.interfaces.rest.transform.EventResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/calendar")
public class CalendarController {
    private final CalendarCommandService calendarCommandService;
    pricate final CalendarQueryService calendarQueryService;

    public CalendarController(CalendarCommandService calendarCommandService, CalendarQueryService calendarQueryService){
        this.calendarCommandService = calendarCommandService;
        this.calendarQueryService =calendarQueryService;
    }

    @PostMapping
    public ResponseEntity<CalendarResource> addCalendar(@RequestBody CreateCalendarResource resource) {
        Optional<Calendar> calendar = this.calendarCommandService.handle(CreateCalendarCommandFromResourceAssembler.toCommandFromResource(resource));
        return calendar.map(source -> ResponseEntity.ok(CalendarResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalendarResource> updateCalendar(@PathVariable Long id, @RequestBody UpdateCategoryResource resource) {
        Optional<Calendar> calendar = this.calendarCommandService.handle(CreateCalendarCommandFromResourceAssembler.toCommandFromResource(resource), id);
        return calendar.map(source -> ResponseEntity.ok(CalendarResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}


