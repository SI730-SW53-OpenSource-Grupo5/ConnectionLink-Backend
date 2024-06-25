package com.connectionlink.backend.notification.interfaces.rest;

import com.connectionlink.backend.notification.domain.model.aggregates.Notification;
import com.connectionlink.backend.notification.domain.model.commands.CreateNotificationCommand;
import com.connectionlink.backend.notification.domain.model.queries.GetAllNotificationByUsernameQuery;
import com.connectionlink.backend.notification.domain.model.queries.GetAllNotificationQuery;
import com.connectionlink.backend.notification.domain.model.queries.GetNotificationByIdQuery;
import com.connectionlink.backend.notification.domain.services.NotificationCommandService;
import com.connectionlink.backend.notification.domain.services.NotificationQueryService;
import com.connectionlink.backend.notification.interfaces.rest.resources.CreateNotificationResource;
import com.connectionlink.backend.notification.interfaces.rest.resources.NotificationResource;
import com.connectionlink.backend.notification.interfaces.rest.resources.UpdateNotificationResource;
import com.connectionlink.backend.notification.interfaces.rest.transform.CreateNotificationCommandFromResourceAssembler;
import com.connectionlink.backend.notification.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import com.connectionlink.backend.notification.interfaces.rest.transform.UpdateNotificationCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;

    public NotificationController(NotificationQueryService notificationQueryService, NotificationCommandService notificationCommandService) {
        this.notificationCommandService = notificationCommandService;
        this.notificationQueryService = notificationQueryService;
    }

    @PostMapping
    public ResponseEntity<NotificationResource> createNotification(@RequestBody CreateNotificationResource command) {
        Optional<Notification> notification = notificationCommandService.handle(CreateNotificationCommandFromResourceAssembler.toCommandFromResource(command));
        return notification.map(source -> ResponseEntity.ok(NotificationResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationResource> updateNotification(@PathVariable Long id, @RequestBody UpdateNotificationResource resource) {
        Optional<Notification> notification = notificationCommandService.handle(UpdateNotificationCommandFromResourceAssembler.toCommandFromResource(resource), id);
        return notification.map(source -> ResponseEntity.ok(NotificationResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<NotificationResource>> getAllNotification() {
        List<Notification> notifications = this.notificationQueryService.handle(new GetAllNotificationQuery());

        if(notifications.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<NotificationResource> notificationsResources = notifications.stream().map(NotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(notificationsResources);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<NotificationResource>> getAllNotificationByUsername(@PathVariable String username) {
        List<Notification> notifications = this.notificationQueryService.handle(new GetAllNotificationByUsernameQuery(username));

        if(notifications.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<NotificationResource> notificationsResources = notifications.stream().map(NotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(notificationsResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResource> getNotificationById(@PathVariable Long id) {
        Optional<Notification> notification = this.notificationQueryService.handle(new GetNotificationByIdQuery(id));

        return notification.map(source -> ResponseEntity.ok(NotificationResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
