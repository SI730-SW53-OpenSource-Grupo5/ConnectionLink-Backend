package com.connectionlink.backend.UserSubscription.infrastructure.persitence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.connectionlink.backend.UserSubscription.domain.model.aggregates.UserSubscription;
import java.util.List;
import java.util.Optional;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {
    List<UserSubscription> findAll();
    List<UserSubscription> findByUserId(Long userId);
    List<UserSubscription> findByUserUsername(String username);
}