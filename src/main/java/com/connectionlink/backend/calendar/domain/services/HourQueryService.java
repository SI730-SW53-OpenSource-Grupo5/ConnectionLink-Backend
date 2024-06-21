package com.connectionlink.backend.calendar.domain.services;

import com.connectionlink.backend.calendar.domain.model.aggregates.Day;
import com.connectionlink.backend.calendar.domain.model.aggregates.Hour;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllDayQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllHourQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetHourByIdQuery;

import java.util.List;
import java.util.Optional;

public interface HourQueryService {
    List<Hour> handle(GetAllHourQuery query);


    Optional<Hour> handle(GetHourByIdQuery query);
}
