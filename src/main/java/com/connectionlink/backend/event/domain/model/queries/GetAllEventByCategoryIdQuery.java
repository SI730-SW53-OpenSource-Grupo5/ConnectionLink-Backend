package com.connectionlink.backend.event.domain.model.queries;

public record GetAllEventByCategoryIdQuery(Long id) {
    public GetAllEventByCategoryIdQuery {
        if(id == null) {
            throw  new IllegalArgumentException("Category Id cannot be null");
        }
    }
}
