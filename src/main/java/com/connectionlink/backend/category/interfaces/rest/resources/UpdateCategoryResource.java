package com.connectionlink.backend.category.interfaces.rest.resources;

public record UpdateCategoryResource(String name, String description) {
    public UpdateCategoryResource {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description must not be null");
        }
    }
}