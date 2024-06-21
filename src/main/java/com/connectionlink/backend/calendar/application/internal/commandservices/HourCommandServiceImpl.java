package com.connectionlink.backend.calendar.application.internal.commandservices;

import com.connectionlink.backend.calendar.domain.model.aggregates.Hour;
import com.connectionlink.backend.calendar.domain.model.commands.*;
import com.connectionlink.backend.calendar.domain.services.HourCommandService;
import com.connectionlink.backend.calendar.infrastructure.persistence.jpa.HourRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HourCommandServiceImpl implements HourCommandService {
    private final HourRepository hourRepository;

    public HourCommandServiceImpl(HourRepository hourRepository) {
        this.hourRepository = hourRepository;
    }
    @Override
    public Optional<Hour> handle(CreateHourCommand command) {
        Optional<Hour> existHour = this.hourRepository.findByHour(command.hour());

        if(existHour.isPresent()) {
            throw new IllegalArgumentException("Hour already exist");
        }

        Hour hour = new Hour(command);
        var hourSaved = this.hourRepository.save(hour);

        return Optional.of(hourSaved);
    }

    @Override
    public Optional<Hour> handle(DeleteHourCommand command) {
        Hour hour = this.hourRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("Day not found"));

        this.hourRepository.delete(hour);
        return Optional.of(hour);
    }

    @Override
    public Optional<Hour> handle(UpdateHourCommand command, Long id) {
        Hour hour = this.hourRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Day not found"));

        hour.setHour(command.hour());

        var hourUpdated = this.hourRepository.save(hour);

        return Optional.of(hourUpdated);
    }
}
