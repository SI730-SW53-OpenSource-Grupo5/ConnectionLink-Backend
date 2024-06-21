package com.connectionlink.backend.calendar.interfaces.rest.transform;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.interfaces.rest.resources.CalendarResource;
import com.connectionlink.backend.user.interfaces.rest.transform.UserResourceFromEntityAssembler;

public class CalendarResourceFromEntityAssembler {
    public static CalendarResource toResourceFromEntity(Calendar entity) {
        return new CalendarResource(entity.getId(), entity.getIsAvailable(), DayResourceFromEntityAssembler.toResourceFromEntity(entity.getDay()), HourResourceFromEntityAssembler.toResourceFromEntity(entity.getHour()),entity.getUrl(), UserResourceFromEntityAssembler.toResourceFromEntity(entity.getSpecialist()));
    }
}
