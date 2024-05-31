package com.connectionlink.backend.category.interfaces.rest.transform;

import com.connectionlink.backend.category.domain.model.commands.CreateCategoryCommand;
import com.connectionlink.backend.category.interfaces.rest.resources.CreateCategoryResource;

public class CreateCategoryCommandFromResourceAssembler {
    public static CreateCategoryCommand toCommandFromResource(CreateCategoryResource resource) {
        return new CreateCategoryCommand(resource.name(), resource.description());
    }
}
