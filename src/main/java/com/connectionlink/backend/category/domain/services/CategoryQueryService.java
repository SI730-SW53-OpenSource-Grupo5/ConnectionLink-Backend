package com.connectionlink.backend.category.domain.services;

import com.connectionlink.backend.category.domain.model.aggregates.Category;
import com.connectionlink.backend.category.domain.model.queries.GetAllCategoryQuery;
import com.connectionlink.backend.category.domain.model.queries.GetCategoryByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CategoryQueryService {
    List<Category> handle(GetAllCategoryQuery query);

    Optional<Category> handle(GetCategoryByIdQuery query);
}
