package com.connectionlink.backend.notification.application.internal.commandservices;

import com.connectionlink.backend.notification.application.internal.queryservices.NotificationQueryServiceImpl;
import com.connectionlink.backend.notification.domain.model.aggregates.Notification;
import com.connectionlink.backend.notification.domain.model.commands.CreateNotificationCommand;
import com.connectionlink.backend.notification.domain.model.commands.UpdateNotificationCommand;
import com.connectionlink.backend.notification.domain.services.NotificationCommandService;
import com.connectionlink.backend.notification.infrastructure.persistence.jpa.NotificationRepository;
import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.connectionlink.backend.user.infrastructure.persitence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Optional<Notification> handle(UpdateNotificationCommand command, Long id) {
        Notification notification = this.notificationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Notification not found"));
        notification.setIsRead(command.isRead());

        var notificationUploaded = this.notificationRepository.save(notification);

        return Optional.of(notificationUploaded);
    }

    @Override
    public Optional<Notification> handle(CreateNotificationCommand command) {
        User user = this.userRepository.findByUsername(command.userUsername()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Notification notification = new Notification(command, user);

        var notificationSaved = this.notificationRepository.save(notification);
        return Optional.of(notificationSaved);
    }
}
