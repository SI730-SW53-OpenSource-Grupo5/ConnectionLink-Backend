package com.connectionlink.backend.event.application.internal.queryservices;

import com.connectionlink.backend.event.domain.model.aggregates.Event;
import com.connectionlink.backend.event.domain.model.queries.*;
import com.connectionlink.backend.event.domain.services.EventQueryService;
import com.connectionlink.backend.event.infrastructure.persitence.jpa.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventQueryServiceImpl implements EventQueryService {
    private final EventRepository eventRepository;

    public EventQueryServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<Event> handle(GetEventByIdQuery query) {
        return this.eventRepository.findById(query.id());
    }

    @Override
    public List<Event> handle(GetAllEventByCategoryIdQuery query) {
        return this.eventRepository.findByCategoryId(query.id());
    }

    @Override
    public List<Event> handle(GetAllEventByUserIdQuery query) {
        return this.eventRepository.findByUsersId(query.id());
    }

    @Override
    public List<Event> handle(GetAllEventByUserUsernameQuery query) {
        return this.eventRepository.findByUsersUsername(query.username());
    }

    @Override
    public List<Event> handle(GetAllEventQuery query) {
        return this.eventRepository.findAll();
    }
}
