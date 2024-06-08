package com.connectionlink.backend.authuser.domain.model.commands;

public record CreateUserAuthCommand(String firstName, String lastName, String phone, String email, String password, String role) {

    public CreateUserAuthCommand {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name is required");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone is required");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("Role is required");
        }
    }

}
