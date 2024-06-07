package com.connectionlink.backend.calendar.domain.services;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.model.commands.ChangeAvailable;

import java.util.Optional;

public interface CalendarCommandService {
    Optional<Calendar> handle(ChangeAvailable command);
}
