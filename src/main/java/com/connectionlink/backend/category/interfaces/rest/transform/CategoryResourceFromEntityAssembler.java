package com.connectionlink.backend.category.interfaces.rest.transform;

import com.connectionlink.backend.category.domain.model.aggregates.Category;
import com.connectionlink.backend.category.interfaces.rest.resources.CategoryResource;

public class CategoryResourceFromEntityAssembler {
    public static CategoryResource toResourceFromEntity(Category entity) {
        return  new CategoryResource(entity.getId(), entity.getName(), entity.getDescription());
    }
}
