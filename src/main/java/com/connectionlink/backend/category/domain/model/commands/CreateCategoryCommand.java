package com.connectionlink.backend.category.domain.model.commands;

public record CreateCategoryCommand(String name, String description) {
    public CreateCategoryCommand {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description must not be null or empty");
        }
    }
}