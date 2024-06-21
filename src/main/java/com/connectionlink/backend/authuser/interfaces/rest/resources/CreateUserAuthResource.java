package com.connectionlink.backend.authuser.interfaces.rest.resources;

public record CreateUserAuthResource(String firstName, String lastName, String phone, String email, String password, String role) {
}
