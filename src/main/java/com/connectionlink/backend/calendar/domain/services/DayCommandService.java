package com.connectionlink.backend.calendar.domain.services;

import com.connectionlink.backend.calendar.domain.model.aggregates.Day;
import com.connectionlink.backend.calendar.domain.model.commands.CreateDayCommand;
import com.connectionlink.backend.calendar.domain.model.commands.DeleteDayCommand;
import com.connectionlink.backend.calendar.domain.model.commands.UpdateDayCommand;

import java.util.Optional;

public interface DayCommandService {
    Optional<Day> handle(CreateDayCommand command);

    Optional<Day> handle(DeleteDayCommand command);

    Optional<Day> handle(UpdateDayCommand command, Long id);
}
