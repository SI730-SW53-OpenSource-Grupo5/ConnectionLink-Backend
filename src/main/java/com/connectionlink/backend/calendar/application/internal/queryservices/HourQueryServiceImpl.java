package com.connectionlink.backend.calendar.application.internal.queryservices;

import com.connectionlink.backend.calendar.domain.model.aggregates.Hour;
import com.connectionlink.backend.calendar.domain.model.queries.GetAllHourQuery;
import com.connectionlink.backend.calendar.domain.model.queries.GetHourByIdQuery;
import com.connectionlink.backend.calendar.domain.services.HourQueryService;
import com.connectionlink.backend.calendar.infrastructure.persistence.jpa.HourRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HourQueryServiceImpl implements HourQueryService {
    private final HourRepository hourRepository;

    public HourQueryServiceImpl(HourRepository hourRepository) {
        this.hourRepository = hourRepository;
    }
    @Override
    public List<Hour> handle(GetAllHourQuery query) {
        return this.hourRepository.findAll();
    }

    @Override
    public Optional<Hour> handle(GetHourByIdQuery query) {
        return this.hourRepository.findById(query.id());
    }
}
