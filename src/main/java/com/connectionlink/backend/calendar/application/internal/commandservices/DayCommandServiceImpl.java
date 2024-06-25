package com.connectionlink.backend.calendar.application.internal.commandservices;

import com.connectionlink.backend.calendar.domain.model.aggregates.Day;
import com.connectionlink.backend.calendar.domain.model.commands.CreateDayCommand;
import com.connectionlink.backend.calendar.domain.model.commands.DeleteDayCommand;
import com.connectionlink.backend.calendar.domain.model.commands.UpdateDayCommand;
import com.connectionlink.backend.calendar.domain.services.DayCommandService;
import com.connectionlink.backend.calendar.infrastructure.persistence.jpa.DayRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DayCommandServiceImpl implements DayCommandService {
    private final DayRepository dayRepository;

    public DayCommandServiceImpl(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }
    @Override
    public Optional<Day> handle(CreateDayCommand command) {
        Optional<Day> existDay = this.dayRepository.findByDay(command.day());

        if(existDay.isPresent()) {
            throw new IllegalArgumentException("Day already exist");
        }

        Day day = new Day(command);
        var daySaved = this.dayRepository.save(day);

        return Optional.of(daySaved);
    }

    @Override
    public Optional<Day> handle(DeleteDayCommand command) {
        Day day = this.dayRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("Day not found"));

        this.dayRepository.delete(day);
        return Optional.of(day);
    }

    @Override
    public Optional<Day> handle(UpdateDayCommand command, Long id) {
        Day day = this.dayRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Day not found"));

        day.setDay(command.day());

        var dayUpdated = this.dayRepository.save(day);

        return Optional.of(dayUpdated);
    }
}
