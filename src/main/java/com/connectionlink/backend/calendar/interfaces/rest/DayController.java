package com.connectionlink.backend.calendar.interfaces.rest;

import com.connectionlink.backend.calendar.domain.model.aggregates.Day;
import com.connectionlink.backend.calendar.domain.model.aggregates.Hour;
import com.connectionlink.backend.calendar.domain.model.commands.CreateDayCommand;
import com.connectionlink.backend.calendar.domain.model.commands.DeleteDayCommand;
import com.connectionlink.backend.calendar.domain.model.commands.DeleteHourCommand;
import com.connectionlink.backend.calendar.domain.model.commands.UpdateDayCommand;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllDayQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetDayByIdQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetHourByIdQuery;
import com.connectionlink.backend.calendar.domain.services.DayCommandService;
import com.connectionlink.backend.calendar.domain.services.DayQueryService;
import com.connectionlink.backend.calendar.interfaces.rest.resources.*;
import com.connectionlink.backend.calendar.interfaces.rest.transform.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/days")
public class DayController {
    private final DayQueryService dayQueryService;
    private final DayCommandService dayCommandService;

    public DayController(DayQueryService dayQueryService, DayCommandService dayCommandService) {
        this.dayQueryService = dayQueryService;
        this.dayCommandService = dayCommandService;
    }

    @GetMapping
    public ResponseEntity<List<DayResource>> getAllDay() {
        List<Day> dayList = this.dayQueryService.handle(new GetAllDayQuery());

        if(dayList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<DayResource> dayResources = dayList.stream().map(DayResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(dayResources);
    }
    @GetMapping({"/{id}"})
    public ResponseEntity<DayResource> getDay(@PathVariable Long id) {
        Optional<Day> day = this.dayQueryService.handle(new GetDayByIdQuery(id));

        if(day.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return day.map(source -> ResponseEntity.ok(DayResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PostMapping
    public ResponseEntity<DayResource> createHour(@RequestBody CreateDayResource resource) {
        Optional<Day> day = this.dayCommandService.handle(CreateDayCommandFromResourceAssembler.toCommandFromResource(resource));
        return day.map(source -> ResponseEntity.ok(DayResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DayResource> updateHour(@RequestBody UpdateDayResource resource, @PathVariable Long id) {
        Optional<Day> day = this.dayCommandService.handle(UpdateDayCommandFromResourceAssembler.toCommandFromResource(resource), id);

        return day.map(source -> ResponseEntity.ok(DayResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<DayResource> deleteHour(@PathVariable Long id) {
        Optional<Day> day = this.dayCommandService.handle(new DeleteDayCommand(id));

        return day.map(source -> ResponseEntity.ok(DayResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }


}
