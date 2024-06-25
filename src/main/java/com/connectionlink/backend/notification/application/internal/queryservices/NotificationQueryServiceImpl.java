package com.connectionlink.backend.notification.application.internal.queryservices;

import com.connectionlink.backend.notification.domain.model.aggregates.Notification;
import com.connectionlink.backend.notification.domain.model.queries.GetAllNotificationByUsernameQuery;
import com.connectionlink.backend.notification.domain.model.queries.GetAllNotificationQuery;
import com.connectionlink.backend.notification.domain.model.queries.GetNotificationByIdQuery;
import com.connectionlink.backend.notification.domain.services.NotificationQueryService;
import com.connectionlink.backend.notification.infrastructure.persistence.jpa.NotificationRepository;
import com.connectionlink.backend.iam.infrastructure.persitence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationQueryServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Notification> handle(GetAllNotificationQuery query) {
        return this.notificationRepository.findAll();
    }

    @Override
    public List<Notification> handle(GetAllNotificationByUsernameQuery query) {
        return this.notificationRepository.findByUserUsername(query.userUsername());
    }

    @Override
    public Optional<Notification> handle(GetNotificationByIdQuery query) {
        return this.notificationRepository.findById(query.id());
    }
}
