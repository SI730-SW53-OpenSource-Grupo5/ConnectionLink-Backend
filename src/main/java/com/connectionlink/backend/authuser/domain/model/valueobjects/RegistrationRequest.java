package com.connectionlink.backend.authuser.domain.model.valueobjects;

public record RegistrationRequest(FirstName firstName, LastName lastName, Phone phone, Email email, Password password, Role role) {
}
