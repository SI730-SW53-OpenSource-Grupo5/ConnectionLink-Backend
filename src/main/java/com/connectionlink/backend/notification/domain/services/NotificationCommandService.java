package com.connectionlink.backend.notification.domain.services;

import com.connectionlink.backend.notification.domain.model.aggregates.Notification;
import com.connectionlink.backend.notification.domain.model.commands.CreateNotificationCommand;
import com.connectionlink.backend.notification.domain.model.commands.UpdateNotificationCommand;

import java.util.Optional;

public interface NotificationCommandService {
    Optional<Notification> handle(UpdateNotificationCommand command, Long id);

    Optional<Notification> handle(CreateNotificationCommand command);

}
