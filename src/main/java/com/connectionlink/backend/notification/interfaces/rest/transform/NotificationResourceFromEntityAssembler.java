package com.connectionlink.backend.notification.interfaces.rest.transform;

import com.connectionlink.backend.notification.domain.model.aggregates.Notification;
import com.connectionlink.backend.notification.interfaces.rest.resources.NotificationResource;
import com.connectionlink.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResourceFromEntity(Notification entity) {
        return new NotificationResource(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getUrl(), entity.getIsRead(), UserResourceFromEntityAssembler.toResourceFromEntity(entity.getUser()));
    }
}
