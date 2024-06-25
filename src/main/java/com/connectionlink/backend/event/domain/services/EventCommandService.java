package com.connectionlink.backend.event.domain.services;

import com.connectionlink.backend.event.domain.model.aggregates.Event;
import com.connectionlink.backend.event.domain.model.commands.AddUserCommand;
import com.connectionlink.backend.event.domain.model.commands.CreateEventCommand;
import com.connectionlink.backend.event.domain.model.commands.RemoveUserCommand;
import com.connectionlink.backend.event.domain.model.commands.UpdateEventCommand;

import java.util.Optional;

public interface EventCommandService {
    Optional<Event> handle(CreateEventCommand command);
    Optional<Event> handle(AddUserCommand command);
    Optional<Event> handle(RemoveUserCommand command);
    Optional<Event> handle(UpdateEventCommand command, Long id);

}
