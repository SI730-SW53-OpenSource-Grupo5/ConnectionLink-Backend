package com.connectionlink.backend.category.application.internal.queryservices;

import com.connectionlink.backend.category.domain.model.aggregates.Category;
import com.connectionlink.backend.category.domain.model.queries.GetAllCategoryQuery;
import com.connectionlink.backend.category.domain.model.queries.GetCategoryByIdQuery;
import com.connectionlink.backend.category.domain.services.CategoryQueryService;
import com.connectionlink.backend.category.infrastructure.persitence.jpa.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryRepository categoryRepository;

    public CategoryQueryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> handle(GetAllCategoryQuery query) {
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<Category> handle(GetCategoryByIdQuery query) {
        return this.categoryRepository.findById(query.id());
    }
}
