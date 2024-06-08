package com.connectionlink.backend.Subscription.interfaces.rest.resources;


public record SubscriptionResource(Long id, String name, String description, String duration, int price) {
}