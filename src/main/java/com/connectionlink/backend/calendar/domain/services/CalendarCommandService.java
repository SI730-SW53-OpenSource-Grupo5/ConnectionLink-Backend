package com.connectionlink.backend.calendar.domain.services;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.model.commands.CreateCalendarCommand;
import com.connectionlink.backend.calendar.domain.model.commands.DeleteCalendaCommand;
import com.connectionlink.backend.calendar.domain.model.commands.UpdateCalendarAvailableCommand;
import com.connectionlink.backend.calendar.domain.model.commands.UpdateCalendarCommand;

import java.util.Optional;

public interface CalendarCommandService {
    Optional<Calendar> handle(CreateCalendarCommand command);

    Optional<Calendar> handle(DeleteCalendaCommand command);

    Optional<Calendar> handle(UpdateCalendarAvailableCommand command, Long id);

    Optional<Calendar> handle(UpdateCalendarCommand command, Long id);
}
