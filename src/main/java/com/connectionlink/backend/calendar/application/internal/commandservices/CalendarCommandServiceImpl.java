package com.connectionlink.backend.calendar.application.internal.commandservices;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.model.aggregates.Day;
import com.connectionlink.backend.calendar.domain.model.aggregates.Hour;
import com.connectionlink.backend.calendar.domain.model.commands.CreateCalendarCommand;
import com.connectionlink.backend.calendar.domain.model.commands.DeleteCalendaCommand;
import com.connectionlink.backend.calendar.domain.model.commands.UpdateCalendarAvailableCommand;
import com.connectionlink.backend.calendar.domain.model.commands.UpdateCalendarCommand;
import com.connectionlink.backend.calendar.domain.services.CalendarCommandService;
import com.connectionlink.backend.calendar.infrastructure.persistence.jpa.CalendarRepository;
import com.connectionlink.backend.calendar.infrastructure.persistence.jpa.DayRepository;
import com.connectionlink.backend.calendar.infrastructure.persistence.jpa.HourRepository;
import com.connectionlink.backend.iam.domain.model.aggregates.User;
import com.connectionlink.backend.iam.infrastructure.persitence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalendarCommandServiceImpl implements CalendarCommandService {
    private final UserRepository userRepository;
    private final CalendarRepository calendarRepository;
    private final DayRepository dayRepository;
    private final HourRepository hourRepository;



    public CalendarCommandServiceImpl(UserRepository userRepository, CalendarRepository calendarRepository, DayRepository dayRepository, HourRepository hourRepository) {
        this.userRepository = userRepository;
        this.calendarRepository = calendarRepository;
        this.dayRepository = dayRepository;
        this.hourRepository = hourRepository;
    }
    @Override
    public Optional<Calendar> handle(CreateCalendarCommand command) {
        User user = this.userRepository.findByUsername(command.specialistUsername()).orElseThrow(() -> new IllegalArgumentException("Specialist User not found"));
        Day day = this.dayRepository.findById(command.dayId()).orElseThrow(() -> new IllegalArgumentException("Day  not found"));
        Hour hour = this.hourRepository.findById(command.hourId()).orElseThrow(() -> new IllegalArgumentException("Hour  not found"));

        Optional<Calendar> calendarExist = this.calendarRepository.findByHourIdAndId(hour.getId(), day.getId());

        if(calendarExist.isPresent()) {
            throw new IllegalArgumentException("Day and Hour already exist in Calendar");
        }

        Calendar calendar = new Calendar(command, day, hour, user);
        var newCalendar = this.calendarRepository.save(calendar);

        return Optional.of(newCalendar);
    }

    @Override
    public Optional<Calendar> handle(DeleteCalendaCommand command) {
       Calendar calendarExist = this.calendarRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("Calendar not found"));

       this.calendarRepository.delete(calendarExist);

        return Optional.of(calendarExist);
    }

    @Override
    public Optional<Calendar> handle(UpdateCalendarAvailableCommand command, Long id) {
        Calendar calendarExist = this.calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Calendar not found"));
        calendarExist.setIsAvailable(command.isAvailable());

        var calendarUpdated = this.calendarRepository.save(calendarExist);

        return Optional.of(calendarUpdated);
    }

    @Override
    public Optional<Calendar> handle(UpdateCalendarCommand command, Long id) {
        Calendar calendarExist = this.calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Calendar not found"));
        Day day = this.dayRepository.findById(command.DayId()).orElseThrow(() -> new IllegalArgumentException("Day  not found"));
        Hour hour = this.hourRepository.findById(command.HourId()).orElseThrow(() -> new IllegalArgumentException("Hour  not found"));

        calendarExist.setDay(day);
        calendarExist.setHour(hour);

        var calendarUpdated = this.calendarRepository.save(calendarExist);

        return Optional.of(calendarUpdated);
    }
}
