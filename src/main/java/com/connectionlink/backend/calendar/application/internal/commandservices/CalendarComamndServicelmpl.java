package com.connectionlink.backend.calendar.application.internal.commandservices;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.domain.model.commands.ChangeAvailable;
import org.springframework.stereotype.Service;
import com.connectionlink.backend.calendar.domain.services.CalendarCommandService;


import java.util.Optional;

@Service
public class CalendarComamndServicelmpl implements CalendarCommandService{

    private final ChangeAvailable changeAvailable;

    public CalendarComamndServicelmpl(ChangeAvailable available) {
        this.available = available;
    }
    @Override
    public Optional<Calendar> handle(ChangeAvailable available){
        Calendar calendar = new Calendar(available);

    }

}
