package com.connectionlink.backend.Subscription.application.internal.queryservices;

import com.connectionlink.backend.Subscription.domain.model.aggregates.Subscription;
import com.connectionlink.backend.Subscription.domain.model.queries.GetAllSubscriptionsQuery;
import com.connectionlink.backend.Subscription.domain.model.queries.GetSubscriptionByIdQuery;
import com.connectionlink.backend.Subscription.domain.services.SubscriptionQueryService;
import com.connectionlink.backend.Subscription.infrastructure.persitence.jpa.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionQueryServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public List<Subscription> handle(GetAllSubscriptionsQuery query) {
        return this.subscriptionRepository.findAll();
    }

    @Override
    public Optional<Subscription> handle(GetSubscriptionByIdQuery query) {
        return this.subscriptionRepository.findById(query.id());
    }
}