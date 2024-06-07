package com.connectionlink.backend.calendar.interfaces.rest;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.services.CalendarCommandService;
import com.connectionlink.backend.calendar.domain.services.CalendarQueryService;
import com.connectionlink.backend.calendar.interfaces.rest.resources.CalendarResource;
import com.connectionlink.backend.calendar.interfaces.rest.resources.ChangeAvailableResource;
import com.connectionlink.backend.calendar.interfaces.rest.trasform.CalendarResourceFromEntityAssembler;
import com.connectionlink.backend.calendar.interfaces.rest.trasform.ChangeAvailebleCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/Calendar")
public class CalendarController {
    private final CalendarCommandService calendarCommandService;
    pricate final CalendarQueryService calendarQueryService;

    public CalendarController(CalendarCommandService calendarCommandService, CalendarQueryService calendarQueryService){
        this.calendarCommandService = calendarCommandService;
        this.calendarQueryService =calendarQueryService;
    }

    @PostMapping
    public ResponseEntity<CalendarResource> addCalendar(@RequestBody ChangeAvailableResource available) {
        Optional<Calendar> calendar = this.calendarCommandService.handle(ChangeAvailebleCommandFromResourceAssembler.toCommandFromResource(available));
        return calendar.map(source -> ResponseEntity.ok(CalendarResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}


