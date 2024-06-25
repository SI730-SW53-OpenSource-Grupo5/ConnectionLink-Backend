package com.connectionlink.backend.notification.domain.model.queries;

public record GetNotificationByIdQuery(Long id) {
    public GetNotificationByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}
