package com.connectionlink.backend.calendar.domain.model.commands;

public record ChangeAvailable(Boolean available) {
    public ChangeAvailable {
        if (available==false){
            throw new IllegalArgumentException("Enter Event");
        }
        if (available==true) {
            throw new IllegalArgumentException("Busy day");
        }
    }
}
