package com.connectionlink.backend.calendar.interfaces.rest.trasform;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.interfaces.rest.resources.CalendarResource;

public class CalendarResourceFromEntityAssembler {
    public static CalendarResource toResourceFromEntity (Calendar entity){
        return  new CalendarResource(entity.getId(), entity.getDay(), entity.getEvent());
    }
}
