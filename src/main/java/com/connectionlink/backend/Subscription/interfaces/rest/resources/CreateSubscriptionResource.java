package com.connectionlink.backend.Subscription.interfaces.rest.resources;

public record CreateSubscriptionResource(String name, String description, String duration, int price) {
    public CreateSubscriptionResource {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (duration == null || duration.isBlank()) {
            throw new IllegalArgumentException("Duration cannot be null or empty");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
    }
}