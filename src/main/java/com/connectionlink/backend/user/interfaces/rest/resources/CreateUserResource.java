package com.connectionlink.backend.user.interfaces.rest.resources;

import java.util.Date;

public record CreateUserResource(String fullName, String username, String description, String profileImageUrl, String bannerImageUrl, String email, String password, Integer age, Date birthday, Boolean isSpecialistUser, String cvUrl) {
    public CreateUserResource {
        if (fullName == null) {
            throw new IllegalArgumentException("fullName cannot be null");
        }
        if (username == null) {
            throw new IllegalArgumentException("username cannot be null");
        }
        if (description == null) {
            throw new IllegalArgumentException("description cannot be null");
        }
        if (profileImageUrl == null) {
            throw new IllegalArgumentException("profileImageUrl cannot be null");
        }
        if (bannerImageUrl == null) {
            throw new IllegalArgumentException("bannerImageUrl cannot be null");
        }
        if (email == null) {
            throw new IllegalArgumentException("email cannot be null");
        }
        if (password == null) {
            throw new IllegalArgumentException("password cannot be null");
        }
        if (age == null) {
            throw new IllegalArgumentException("age cannot be null");
        }
        if (birthday == null) {
            throw new IllegalArgumentException("birthday cannot be null");
        }
        if (isSpecialistUser == null) {
            throw new IllegalArgumentException("isSpecialistUser cannot be null");
        }
        if (cvUrl == null) {
            throw new IllegalArgumentException("cvUrl cannot be null");
        }
    }
}