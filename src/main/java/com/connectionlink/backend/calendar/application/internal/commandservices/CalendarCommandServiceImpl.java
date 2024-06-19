package com.connectionlink.backend.calendar.application.internal.commandservices;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.model.commands.CreateCalendarCommand;
import com.connectionlink.backend.calendar.domain.services.CalendarCommandService;
import com.connectionlink.backend.calendar.infraestructure.persistence.jpa.CalendarRepository;
import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.connectionlink.backend.user.infrastructure.persitence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalendarCommandServiceImpl implements CalendarCommandService {

    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    public CalendarCommandServiceImpl(CalendarRepository calendarRepository, UserRepository userRepository) {
        this.calendarRepository = calendarRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Calendar> handle(CreateCalendarCommand command) {
        User user = this.userRepository.findByUsername(command.username()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Calendar calendar = new Calendar(command, user);
        if(!user.getIsSpecialistUser()) {
            throw new IllegalArgumentException("User is not a specialist");
        }
        var calendarSaved = this.calendarRepository.save(calendar);
        return Optional.of(calendarSaved);
    }
}
