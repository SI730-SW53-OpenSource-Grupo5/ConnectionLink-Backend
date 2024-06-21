package com.connectionlink.backend.authuser.domain.model.aggregates;

import com.connectionlink.backend.authuser.domain.model.commands.CreateUserAuthCommand;
import com.connectionlink.backend.authuser.domain.model.valueobjects.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)  // it is used to automatically populate the createdAt and updatedAt fields
public class UserAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    @Embedded
    private FirstName firstName;

    @Column(nullable = false)
    @Embedded
    private LastName lastName;

    @Column(nullable = false)
    @Embedded
    private Phone phone;

    @Column(nullable = false)
    @Embedded
    private Email email;

    @Column(nullable = false)
    @Embedded
    private Password password;

    @Column(nullable = false)
    @Embedded
    private Role role;

    protected UserAuth() { }

    public UserAuth(RegistrationRequest registrationRequest) {
        this.firstName = registrationRequest.firstName();
        this.lastName = registrationRequest.lastName();
        this.phone = registrationRequest.phone();
        this.email = registrationRequest.email();
        this.password = registrationRequest.password();
        this.role = registrationRequest.role();
    }

    public UserAuth(CreateUserAuthCommand command) {
        this.firstName = new FirstName(command.firstName());
        this.lastName = new LastName(command.lastName());
        this.phone = new Phone(command.phone());
        this.email = new Email(command.email());
        this.password = new Password(command.password());
        this.role = new Role(command.role());
    }

    public String getFirstName() {return firstName.getFirstName();}
    public String getLastName() {return lastName.getLastName();}
    public String getRole() {return role.getRole();}
    public String getEmail() {return email.getEmail();}
    public String getPassword() {return password.getPassword();}
    public String getPhone() {return phone.getPhone();}

}
