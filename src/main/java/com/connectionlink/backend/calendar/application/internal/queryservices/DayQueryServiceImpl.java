package com.connectionlink.backend.calendar.application.internal.queryservices;

import com.connectionlink.backend.calendar.domain.model.aggregates.Day;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllDayQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetDayByIdQuery;
import com.connectionlink.backend.calendar.domain.services.DayQueryService;
import com.connectionlink.backend.calendar.infrastructure.persistence.jpa.DayRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DayQueryServiceImpl implements DayQueryService {
    private final DayRepository dayRepository;

    public DayQueryServiceImpl(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }
    @Override
    public List<Day> handle(GetAllDayQuery query) {
        return this.dayRepository.findAll();
    }

    @Override
    public Optional<Day> handle(GetDayByIdQuery query) {
        return this.dayRepository.findById(query.id());
    }
}
