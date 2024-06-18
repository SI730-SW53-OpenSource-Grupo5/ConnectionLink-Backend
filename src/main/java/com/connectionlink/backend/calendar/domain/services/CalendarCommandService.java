package com.connectionlink.backend.calendar.domain.services;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.model.commands.CreateCalendarCommand;
import com.connectionlink.backend.calendar.domain.model.commands.UpdateCalendarCommand;


import java.util.Optional;

public interface CalendarCommandService {
    Optional<Calendar> handle(CreateCalendarCommand command);

    Optional<Calendar> handle(UpdateCalendarCommand command,Long id);
}
