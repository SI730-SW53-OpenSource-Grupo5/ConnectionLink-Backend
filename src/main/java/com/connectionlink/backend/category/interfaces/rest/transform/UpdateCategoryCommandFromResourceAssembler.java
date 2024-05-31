package com.connectionlink.backend.category.interfaces.rest.transform;

import com.connectionlink.backend.category.domain.model.commands.UpdateCategoryComamnd;
import com.connectionlink.backend.category.interfaces.rest.resources.UpdateCategoryResource;

public class UpdateCategoryCommandFromResourceAssembler {
    public static UpdateCategoryComamnd toCommandFromResource(UpdateCategoryResource resource) {
        return new UpdateCategoryComamnd(resource.name(), resource.description());
    }
}
