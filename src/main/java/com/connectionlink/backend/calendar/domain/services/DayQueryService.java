package com.connectionlink.backend.calendar.domain.services;

import com.connectionlink.backend.calendar.domain.model.aggregates.Day;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllDayQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetDayByIdQuery;

import java.util.List;
import java.util.Optional;

public interface DayQueryService {
    List<Day> handle(GetAllDayQuery query);

    Optional<Day> handle(GetDayByIdQuery query);
}
