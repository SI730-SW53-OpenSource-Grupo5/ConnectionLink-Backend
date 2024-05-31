package com.connectionlink.backend.user.domain.model.aggregates;

import com.connectionlink.backend.user.domain.model.commands.CreateUserCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class) // it is used to automatically populate the createdAt and updatedAt fields
public class User extends AbstractAggregateRoot<User> {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Getter
    private Long Id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String fullName;

    @Column(nullable = false)
    @Getter
    @Setter
    private String username;

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
    private String email;

    @Column(nullable = false)
    @Getter
    @Setter
    private String password;

    @Column(nullable = false)
    @Getter
    @Setter
    private Integer age;

    @Column(nullable = false)
    @Getter
    @Setter
    private Date birthday;

    @Column(nullable = false)
    @Getter
    @Setter
    private Boolean isSpecialistUser;

    @Column(nullable = false)
    @Getter
    @Setter
    private String cvUrl;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Getter
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;


    protected User() {}

    public User(CreateUserCommand command) {
        this.fullName = command.fullName();
        this.username = command.username();
        this.description = command.description();
        this.profileImageUrl = command.profileImageUrl();
        this.bannerImageUrl = command.bannerImageUrl();
        this.email = command.email();
        this.password = command.password();
        this.age = command.age();
        this.birthday = command.birthday();
        this.isSpecialistUser = command.isSpecialistUser();
        this.cvUrl = command.cvUrl();
    }
}
