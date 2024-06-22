package com.connectionlink.backend.iam.domain.model.commands;

import com.connectionlink.backend.iam.domain.model.entities.Role;

import java.util.Date;
import java.util.List;

public record SignUpCommand(String fullName, String username, String description, String profileImageUrl, String bannerImageUrl, String email, String password, Integer age, Date birthday, Boolean isSpecialistUser, String cvUrl, List<Role> roles) {
    public SignUpCommand {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("fullName cannot be null or empty");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username cannot be null or empty");
        }
        if (description == null) {
            throw new IllegalArgumentException("description cannot be null");
        }
        if (profileImageUrl == null ) {
            throw new IllegalArgumentException("profileImageUrl cannot be null");
        }
        if (bannerImageUrl == null) {
            throw new IllegalArgumentException("bannerImageUrl cannot be null");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be null or empty");
        }
        if (age == null || age <= 0) {
            throw new IllegalArgumentException("age must be a positive integer");
        }
        if (birthday == null) {
            throw new IllegalArgumentException("birthday cannot be null");
        }
        if (isSpecialistUser == null) {
            throw new IllegalArgumentException("isSpecialistUser cannot be null");
        }
        if (cvUrl == null ) {
            throw new IllegalArgumentException("cvUrl cannot be null");
        }
    }
}
