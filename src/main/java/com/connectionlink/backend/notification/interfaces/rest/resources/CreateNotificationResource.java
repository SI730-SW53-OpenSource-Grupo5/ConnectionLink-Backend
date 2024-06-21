package com.connectionlink.backend.notification.interfaces.rest.resources;

public record CreateNotificationResource(String title, String description, String url, String userUsername) {
}
