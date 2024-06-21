package com.connectionlink.backend.calendar.interfaces.rest.transform;

import com.connectionlink.backend.calendar.domain.model.aggregates.Day;
import com.connectionlink.backend.calendar.interfaces.rest.resources.DayResource;

public class DayResourceFromEntityAssembler {
    public static DayResource toResourceFromEntity(Day entity) {
        return new DayResource(entity.getId(), entity.getDay());
    }
}
