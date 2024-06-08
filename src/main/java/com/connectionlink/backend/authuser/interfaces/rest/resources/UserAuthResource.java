package com.connectionlink.backend.authuser.interfaces.rest.resources;

public record UserAuthResource(String firstName, String lastName, String phone, String email, String password, String role) {

}
