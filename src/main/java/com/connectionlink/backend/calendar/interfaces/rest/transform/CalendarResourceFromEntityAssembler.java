package com.connectionlink.backend.calendar.interfaces.rest.transform;

import com.connectionlink.backend.calendar.domain.model.aggregates.Calendar;
import com.connectionlink.backend.calendar.interfaces.rest.resources.CalendarResource;
import com.connectionlink.backend.user.interfaces.rest.resources.UserResource;
import com.connectionlink.backend.user.interfaces.rest.transform.UserResourceFromEntityAssembler;

public class CalendarResourceFromEntityAssembler {

    public static  CalendarResource toResourceFromEntity(Calendar entity) {
        UserResource specialist = UserResourceFromEntityAssembler.toResourceFromEntity(entity.getSpecialist());

        return new CalendarResource(entity.getId(), specialist, entity.getDay(), entity.isAvailable());
    }
}
