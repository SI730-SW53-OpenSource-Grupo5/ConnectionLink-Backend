package com.connectionlink.backend.category.domain.services;

import com.connectionlink.backend.category.domain.model.aggregates.Category;
import com.connectionlink.backend.category.domain.model.commands.CreateCategoryCommand;
import com.connectionlink.backend.category.domain.model.commands.UpdateCategoryComamnd;

import java.util.Optional;

public interface CategoryCommandService {
    Optional<Category> handle(CreateCategoryCommand command);
    Optional<Category> handle(UpdateCategoryComamnd command, Long id);

}
