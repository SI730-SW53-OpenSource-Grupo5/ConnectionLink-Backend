package com.connectionlink.backend.event.interfaces.rest;

import com.connectionlink.backend.event.domain.model.aggregates.Event;
import com.connectionlink.backend.event.domain.model.queries.*;
import com.connectionlink.backend.event.domain.services.EventCommandService;
import com.connectionlink.backend.event.domain.services.EventQueryService;
import com.connectionlink.backend.event.interfaces.rest.resources.*;
import com.connectionlink.backend.event.interfaces.rest.transform.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/events")
public class EventController {
    private final EventCommandService eventCommandService;
    private final EventQueryService eventQueryService;

    public EventController(EventCommandService eventCommandService, EventQueryService eventQueryService) {
        this.eventCommandService = eventCommandService;
        this.eventQueryService = eventQueryService;
    }

    @PostMapping
    public ResponseEntity<EventResource> createEvent(@RequestBody CreateEventResource resource) {
        Optional<Event> event = eventCommandService.handle(CreateEventComamndFromResourceAssembler.toCommandFromResource(resource));
        return event.map(source -> ResponseEntity.ok(EventResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/user")
    public ResponseEntity<EventResource> addUserOnEvent(@RequestBody AddUserResource resource) {
        Optional<Event> event = eventCommandService.handle(AddUserCommandFromResourceAssembler.toCommandFromResource(resource));
        return event.map(source -> ResponseEntity.ok(EventResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResource> updateEvent(@PathVariable Long id, @RequestBody UpdateEventResource resource) {
        Optional<Event> event = eventCommandService.handle(UpdateEventCommandFromResourceAssembler.toCommandFromResource(resource), id);
        return event.map(source -> ResponseEntity.ok(EventResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/user")
    public ResponseEntity<EventResource> deleteUserEvent(@RequestBody RemoveUserResource resource) {
        Optional<Event> event = eventCommandService.handle(RemoveEventCommandFromResourceAssembler.toCommandFromResource(resource));
        return event.map(source -> ResponseEntity.ok(EventResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("")
    public ResponseEntity<List<EventResource>> getAllEvent() {
        List<Event> events = eventQueryService.handle(new GetAllEventQuery());

        if(events.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EventResource> eventsResource = events.stream().map(EventResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(eventsResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResource> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventQueryService.handle(new GetEventByIdQuery(id));

        return event.map(source -> ResponseEntity.ok(EventResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<EventResource>> getAllEventByUserId(@PathVariable Long id) {
        List<Event> events = eventQueryService.handle(new GetAllEventByUserIdQuery(id));

        if(events.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EventResource> eventsResource = events.stream().map(EventResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(eventsResource);
    }

    @GetMapping("/user/username/{username}")
    public ResponseEntity<List<EventResource>> getAllEventByUserUsername(@PathVariable String username) {
        List<Event> events = eventQueryService.handle(new GetAllEventByUserUsernameQuery(username));

        if(events.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EventResource> eventsResource = events.stream().map(EventResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(eventsResource);
    }

    @GetMapping("/specialist/username/{username}")
    public ResponseEntity<List<EventResource>> getAllEventBySpecialistUsername(@PathVariable String username) {
        List<Event> events = eventQueryService.handle(new GetAllEventBySpecialistUsernameQuery(username));

        if(events.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EventResource> eventsResource = events.stream().map(EventResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(eventsResource);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<EventResource>> getAllEventByCategoryId(@PathVariable Long id) {
        List<Event> events = eventQueryService.handle(new GetAllEventByCategoryIdQuery(id));

        if(events.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EventResource> eventsResource = events.stream().map(EventResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(eventsResource);
    }
}
