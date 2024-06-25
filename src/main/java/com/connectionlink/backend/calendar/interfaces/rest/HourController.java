package com.connectionlink.backend.calendar.interfaces.rest;

import com.connectionlink.backend.calendar.domain.model.aggregates.Hour;
import com.connectionlink.backend.calendar.domain.model.commands.DeleteHourCommand;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllHourQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetHourByIdQuery;
import com.connectionlink.backend.calendar.domain.services.HourCommandService;
import com.connectionlink.backend.calendar.domain.services.HourQueryService;
import com.connectionlink.backend.calendar.interfaces.rest.resources.CreateHourResource;
import com.connectionlink.backend.calendar.interfaces.rest.resources.DeleteHourResource;
import com.connectionlink.backend.calendar.interfaces.rest.resources.HourResource;
import com.connectionlink.backend.calendar.interfaces.rest.resources.UpdateHourResource;
import com.connectionlink.backend.calendar.interfaces.rest.transform.CreateHourCommandFromResourceAssembler;
import com.connectionlink.backend.calendar.interfaces.rest.transform.HourResourceFromEntityAssembler;
import com.connectionlink.backend.calendar.interfaces.rest.transform.UpdateHourCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/hours")
public class HourController {
    private final HourQueryService hourQueryService;
    private final HourCommandService hourCommandService;

    public HourController(HourQueryService hourQueryService, HourCommandService hourCommandService) {
        this.hourCommandService = hourCommandService;
        this.hourQueryService = hourQueryService;
    }

    @GetMapping
    public ResponseEntity<List<HourResource>> getAllHour() {
        List<Hour> hourList = this.hourQueryService.handle(new GetAllHourQuery());

        if(hourList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<HourResource> hourResources = hourList.stream().map(HourResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(hourResources);
    }
    @GetMapping({"/{id}"})
    public ResponseEntity<HourResource> getHour(@PathVariable Long id) {
        Optional<Hour> hour = this.hourQueryService.handle(new GetHourByIdQuery(id));

        if(hour.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return hour.map(source -> ResponseEntity.ok(HourResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PostMapping
    public ResponseEntity<HourResource> createHour(@RequestBody CreateHourResource resource) {
        Optional<Hour> hour = this.hourCommandService.handle(CreateHourCommandFromResourceAssembler.toCommandFromResource(resource));
        return hour.map(source -> ResponseEntity.ok(HourResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HourResource> updateHour(@RequestBody UpdateHourResource resource, @PathVariable Long id) {
        Optional<Hour> hour = this.hourCommandService.handle(UpdateHourCommandFromResourceAssembler.toCommandFromResource(resource), id);

        return hour.map(source -> ResponseEntity.ok(HourResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<HourResource> deleteHour(@PathVariable Long id) {
        Optional<Hour> hour = this.hourCommandService.handle(new DeleteHourCommand(id));

        return hour.map(source -> ResponseEntity.ok(HourResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }


}
