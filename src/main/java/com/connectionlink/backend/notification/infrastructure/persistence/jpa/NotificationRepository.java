package com.connectionlink.backend.notification.infrastructure.persistence.jpa;

import com.connectionlink.backend.notification.domain.model.aggregates.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<Notification> findById(Long id);

    List<Notification> findByUserUsername(String userUsername);

    List<Notification> findAll();

}
