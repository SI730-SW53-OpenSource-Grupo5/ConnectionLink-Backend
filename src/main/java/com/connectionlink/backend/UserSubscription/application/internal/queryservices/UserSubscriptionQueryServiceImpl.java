package com.connectionlink.backend.UserSubscription.application.internal.queryservices;

import com.connectionlink.backend.UserSubscription.domain.model.aggregates.UserSubscription;
import com.connectionlink.backend.UserSubscription.domain.model.queries.GetAllUserSubscriptionByUserUsernameQuery;
import com.connectionlink.backend.UserSubscription.domain.model.queries.GetAllUserSubscriptionByUserIdQuery;
import com.connectionlink.backend.UserSubscription.domain.services.UserSubscriptionQueryService;
import com.connectionlink.backend.UserSubscription.infrastructure.persitence.jpa.UserSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserSubscriptionQueryServiceImpl implements UserSubscriptionQueryService {
    private final UserSubscriptionRepository userSubscriptionRepository;

    @Autowired
    public UserSubscriptionQueryServiceImpl(UserSubscriptionRepository userSubscriptionRepository) {
        this.userSubscriptionRepository = userSubscriptionRepository;
    }

    @Override
    public List<UserSubscription> handle(GetAllUserSubscriptionByUserIdQuery query) {
        return userSubscriptionRepository.findByUserId(query.userId());
    }

    @Override
    public List<UserSubscription> handle(GetAllUserSubscriptionByUserUsernameQuery query) {
        return userSubscriptionRepository.findByUserUsername(query.username());
    }
}