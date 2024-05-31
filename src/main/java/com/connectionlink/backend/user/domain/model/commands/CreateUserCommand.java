package com.connectionlink.backend.user.domain.model.commands;

import java.util.Date;

/**
 * Command to create a User.
 *
 * <p>The CreateUserCommand record encapsulates the data required to create a User.
 * It validates that the fullName, email, password, profileImageUrl, and bannerImageUrl are not null or empty.
 * It also validates that age is positive and birthday is not null.</p>
 *
 * @param fullName The full name of the user.
 * @param description A brief description of the user.
 * @param profileImageUrl The URL of the user's profile image.
 * @param bannerImageUrl The URL of the user's banner image.
 * @param email The email address of the user.
 * @param password The password for the user.
 * @param age The age of the user.
 * @param birthday The birthday of the user.
 * @param isSpecialistUser Flag indicating if the user is a specialist user.
 * @param cvUrl The URL of the user's CV.
 * @throws IllegalArgumentException if fullName, username, email, password are null or empty,
 *                                  if age is non-positive, if birthday, profileImageUrl, bannerImageUrl or cvUrl  are null, or if isSpecialistUserm is null.
 */

public record CreateUserCommand(String fullName, String username, String description, String profileImageUrl, String bannerImageUrl, String email, String password, Integer age, Date birthday, Boolean isSpecialistUser, String cvUrl) {
    public CreateUserCommand {
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
