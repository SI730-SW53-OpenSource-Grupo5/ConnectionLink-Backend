package com.connectionlink.backend.UserSubscription.domain.services;

import com.connectionlink.backend.UserSubscription.domain.model.aggregates.UserSubscription;
import com.connectionlink.backend.UserSubscription.domain.model.queries.GetAllUserSubscriptionByUserIdQuery;
import com.connectionlink.backend.UserSubscription.domain.model.queries.GetAllUserSubscriptionByUserUsernameQuery;
import com.connectionlink.backend.UserSubscription.infrastructure.persitence.jpa.UserSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserSubscriptionQueryService {
    List<UserSubscription> handle(GetAllUserSubscriptionByUserIdQuery query);

    List<UserSubscription> handle(GetAllUserSubscriptionByUserUsernameQuery query);
}