package com.connectionlink.backend.UserSubscription.interfaces.rest.resources;


import java.time.LocalDate;

public record UserSubscriptionResource(Long id, Long userId, Long subscriptionId, String startDate, String endDate) {
}
