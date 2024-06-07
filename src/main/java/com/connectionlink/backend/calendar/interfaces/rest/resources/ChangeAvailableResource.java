package com.connectionlink.backend.calendar.interfaces.rest.resources;

public record ChangeAvailableResource(Boolean available) {
    public ChangeAvailableResource {
        if (available==false){
            throw new IllegalArgumentException("Enter Event");
        }
        if (available==true) {
            throw new IllegalArgumentException("Busy day");
        }
    }
}
