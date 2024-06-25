package com.connectionlink.backend.event.domain.services;

import com.connectionlink.backend.event.domain.model.aggregates.Event;
import com.connectionlink.backend.event.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface EventQueryService {
    Optional<Event> handle(GetEventByIdQuery query);

    List<Event> handle(GetAllEventByCategoryIdQuery query);

    List<Event> handle(GetAllEventByUserIdQuery query);

    List<Event> handle(GetAllEventByUserUsernameQuery query);

    List<Event> handle(GetAllEventBySpecialistUsernameQuery query);

    List<Event> handle(GetAllEventQuery query);
}
