package com.connectionlink.backend.event.domain.model.aggregates;

import com.connectionlink.backend.category.domain.model.aggregates.Category;
import com.connectionlink.backend.event.domain.model.commands.CreateEventCommand;
import com.connectionlink.backend.iam.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class) // it is used to automatically populate the createdAt and updatedAt fields
public class Event {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Getter
    private Long Id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String title;

    @Column(nullable = false)
    @Getter
    @Setter
    private String description;

    @Column(nullable = false)
    @Getter
    @Setter
    private String profileImageUrl;

    @Column(nullable = false)
    @Getter
    @Setter
    private String bannerImageUrl;

    @Column(nullable = false)
    @Getter
    @Setter
    private Date day;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @Getter
    @Setter
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    @Setter
    private User specialist;

    @ManyToMany
    @JoinTable(
            name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Getter
    @Setter
    private List<User> users;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    protected Event() {
    }

    public Event(CreateEventCommand command, Category category, User user) {
        this.title = command.title();
        this.description = command.description();
        this.profileImageUrl = command.profileImageUrl();
        this.bannerImageUrl = command.bannerImageUrl();
        this.day = command.day();
        this.category = category;
        this.users = new ArrayList<User>();
        this.specialist = user;
    }
}
