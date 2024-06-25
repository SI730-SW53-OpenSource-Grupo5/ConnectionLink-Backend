package com.connectionlink.backend.notification.interfaces.rest.resources;

import com.connectionlink.backend.iam.interfaces.rest.resources.UserResource;

public record NotificationResource(Long id, String title, String description, String url, Boolean isRead, UserResource user) {
}
