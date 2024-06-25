package com.connectionlink.backend.iam.interfaces.rest.resources;

import java.util.Date;

public record AuthenticationUserResource(Long id, String fullName, String username, String description, String profileImageUrl, String bannerImageUrl, String email, Integer age, Date birthday, Boolean isSpecialistUser, String cvUrl, String token) {
}
