package com.connectionlink.backend.category.domain.model.queries;

public record GetCategoryByIdQuery(Long id) {
    public GetCategoryByIdQuery {
        if(id == null) {
            throw  new IllegalArgumentException("Category id cannot be null");
        }
    }
}
