package com.connectionlink.backend.calendar.interfaces.rest.transform;

import com.connectionlink.backend.calendar.domain.model.aggregates.Hour;
import com.connectionlink.backend.calendar.interfaces.rest.resources.HourResource;

public class HourResourceFromEntityAssembler {
    public static HourResource toResourceFromEntity(Hour entity) {
        return new HourResource(entity.getId(), entity.getHour());
    }
}
