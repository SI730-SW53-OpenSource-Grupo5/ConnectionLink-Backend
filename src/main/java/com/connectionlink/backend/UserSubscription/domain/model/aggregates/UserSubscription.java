package com.connectionlink.backend.UserSubscription.domain.model.aggregates;

import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.connectionlink.backend.Subscription.domain.model.aggregates.Subscription;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    @Getter
    @Setter
    private Subscription subscription;

    @Column(nullable = false)
    @Getter
    @Setter
    private LocalDate startDate;

    @Column(nullable = false)
    @Getter
    @Setter
    private LocalDate endDate;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    protected UserSubscription() {
    }

    public UserSubscription(User user, Subscription subscription, LocalDate startDate, LocalDate endDate) {
        this.user = user;
        this.subscription = subscription;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
