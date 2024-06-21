package com.connectionlink.backend.notification.interfaces.rest.transform;

import com.connectionlink.backend.notification.domain.model.commands.UpdateNotificationCommand;
import com.connectionlink.backend.notification.interfaces.rest.resources.UpdateNotificationResource;

public class UpdateNotificationCommandFromResourceAssembler {
    public static UpdateNotificationCommand toCommandFromResource(UpdateNotificationResource resource) {
        return new UpdateNotificationCommand(resource.isRead());
    }
}
