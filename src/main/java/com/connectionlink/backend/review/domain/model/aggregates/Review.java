package com.connectionlink.backend.review.domain.model.aggregates;

import com.connectionlink.backend.review.domain.model.commands.CreateReviewCommand;
import com.connectionlink.backend.iam.domain.model.aggregates.User;
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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, updatable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "specialist_id", nullable = false)
    @Getter
    @Setter
    private User specialist;

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

    protected Review() {}
    public Review(CreateReviewCommand command, User specialist, User user) {
        this.description = command.description();
        this.specialist = specialist;
        this.user = user;
    }
}
