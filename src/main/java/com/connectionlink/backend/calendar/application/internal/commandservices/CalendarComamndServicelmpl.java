package com.connectionlink.backend.calendar.application.internal.commandservices;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.model.commands.CreateCalendarCommand;
import com.connectionlink.backend.calendar.domain.model.commands.UpdateCalendarCommand;
import com.connectionlink.backend.category.interfaces.rest.jpa.CalendarRepository;
import com.connectionlink.backend.event.infrastructure.persitence.jpa.EventRepository;
import org.springframework.stereotype.Service;
import com.connectionlink.backend.calendar.domain.services.CalendarCommandService;

import java.util.Optional;

@Service
public class CalendarComamndServicelmpl implements CalendarCommandService {

    private final CalendarRepository calendarRepository;

    private final EventRepository eventRepository;

    public CalendarComamndServicelmpl(CalendarRepository calendarRepository,EventRepository eventRepository){
        this.calendarRepository = calendarRepository;
        this.eventRepository= eventRepository;
    }

    @Override
    public Optional<Calendar> handle(CreateCalendarCommand command){
        Calendar calendar = new Calendar(command);

        var calendarSaved = calendarRepository.save(calendar);
        return Optional.of(calendarSaved);
    }

    @Override
    public Optional<Calendar> handle(UpdateCalendarCommand command, long id){
        Calendar calendar = this.calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));

        calendar.setEvent(command.event());
        calendar.setDay(command.day());

        var calendarUpdated = this.calendarRepository.save(calendar);

        return Optional.of(calendarUpdated);
    }
}




