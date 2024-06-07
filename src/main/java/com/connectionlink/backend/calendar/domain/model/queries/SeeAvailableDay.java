package com.connectionlink.backend.calendar.domain.model.queries;

public record SeeAvailableDay(Boolean available) {
    public SeeAvailableDay{
        if (available==false){
            throw new IllegalArgumentException("Day off");
        }
        if (available==true) {
            throw new IllegalArgumentException("Busy day");
        }
    }
}
