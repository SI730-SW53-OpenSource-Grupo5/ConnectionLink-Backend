package com.connectionlink.backend.calendar.domain.services;

import com.connectionlink.backend.calendar.domain.model.aggregates.Hour;
import com.connectionlink.backend.calendar.domain.model.commands.*;

import java.util.Optional;

public interface HourCommandService {
    Optional<Hour> handle(CreateHourCommand command);

    Optional<Hour> handle(DeleteHourCommand command);

    Optional<Hour> handle(UpdateHourCommand command, Long id);
}
