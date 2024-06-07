package com.connectionlink.backend.calendar.domain.services;

import com.connectionlink.backend.calendar.domain.model.queries.SeeAvailableDay;

import java.lang.ref.Cleaner;
import java.util.List;
public interface CalendarQueryService {
    List<Cleaner> handle(SeeAvailableDay query);
}
