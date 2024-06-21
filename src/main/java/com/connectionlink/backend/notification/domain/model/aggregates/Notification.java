package com.connectionlink.backend.notification.domain.model.aggregates;

import com.connectionlink.backend.notification.domain.model.commands.CreateNotificationCommand;
import com.connectionlink.backend.review.domain.model.commands.CreateReviewCommand;
import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.connectionlink.backend.user.domain.model.commands.CreateUserCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, updatable = false)
    private String title;

    @Column(nullable = false, updatable = false)
    private String description;

    @Column(nullable = false, updatable = false)
    private String url;

    @Column(nullable = false)
    @Getter
    @Setter
    private Boolean isRead;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    @Setter
    private User user;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    protected Notification() {}
    public Notification(CreateNotificationCommand command, User user) {
        this.title = command.title();
        this.description = command.description();
        this.user = user;
        this.url = command.url();
        this.isRead = false;
    }
}
