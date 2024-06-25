package com.connectionlink.backend.notification.domain.services;

import com.connectionlink.backend.notification.domain.model.aggregates.Notification;
import com.connectionlink.backend.notification.domain.model.queries.GetAllNotificationByUsernameQuery;
import com.connectionlink.backend.notification.domain.model.queries.GetAllNotificationQuery;
import com.connectionlink.backend.notification.domain.model.queries.GetNotificationByIdQuery;

import java.util.List;
import java.util.Optional;

public interface NotificationQueryService {
    List<Notification> handle(GetAllNotificationQuery query);

    List<Notification> handle(GetAllNotificationByUsernameQuery query);

    Optional<Notification> handle(GetNotificationByIdQuery query);
}
