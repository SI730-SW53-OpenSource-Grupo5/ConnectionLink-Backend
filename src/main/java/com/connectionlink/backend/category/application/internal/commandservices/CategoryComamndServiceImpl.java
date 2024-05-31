package com.connectionlink.backend.category.application.internal.commandservices;

import com.connectionlink.backend.category.domain.model.aggregates.Category;
import com.connectionlink.backend.category.domain.model.commands.CreateCategoryCommand;
import com.connectionlink.backend.category.domain.model.commands.UpdateCategoryComamnd;
import com.connectionlink.backend.category.domain.services.CategoryCommandService;
import com.connectionlink.backend.category.infrastructure.persitence.jpa.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryComamndServiceImpl implements CategoryCommandService {

    private final CategoryRepository categoryRepository;

    public CategoryComamndServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Optional<Category> handle(CreateCategoryCommand command) {

        Category category = new Category(command);

        var categorySaved = categoryRepository.save(category);
        return Optional.of(categorySaved);
    }

    @Override
    public Optional<Category> handle(UpdateCategoryComamnd command, Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));

        category.setName(command.name());
        category.setDescription(command.description());

        var categoryUpdated = this.categoryRepository.save(category);

        return Optional.of(categoryUpdated);
    }
}
