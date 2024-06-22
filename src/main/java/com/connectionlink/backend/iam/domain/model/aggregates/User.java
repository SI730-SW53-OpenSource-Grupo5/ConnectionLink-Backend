package com.connectionlink.backend.iam.domain.model.aggregates;

import com.connectionlink.backend.iam.domain.model.commands.CreateUserCommand;
import com.connectionlink.backend.iam.domain.model.entities.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(nullable = false, unique = true)
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

    @Getter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

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


    protected User() {
        this.roles = new HashSet<>();
    }


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
        this.roles = new HashSet<>();
    }

    public User(CreateUserCommand command, List<Role> roles) {
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
        this.roles = new HashSet<>();
        this.addRoles(roles);
    }

    public void addRoles(List<Role> roles) {
        var validateRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validateRoleSet);
    }

}
