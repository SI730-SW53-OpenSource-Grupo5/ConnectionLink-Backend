package com.connectionlink.backend.Subscription.infrastructure.persitence.jpa;

import com.connectionlink.backend.Subscription.domain.model.aggregates.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import com.connectionlink.backend.UserSubscription.domain.model.aggregates.UserSubscription;
import java.util.List;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findAll();
    Optional<Subscription> findById(Long id);
    List<Subscription> findByNameContaining(String name);
    List<Subscription> findByPriceLessThanEqual(int price);
}