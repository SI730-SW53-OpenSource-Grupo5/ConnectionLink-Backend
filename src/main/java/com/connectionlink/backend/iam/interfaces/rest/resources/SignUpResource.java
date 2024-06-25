package com.connectionlink.backend.iam.interfaces.rest.resources;

import java.util.Date;
import java.util.List;

public record SignUpResource(String fullName, String username, String description, String profileImageUrl, String bannerImageUrl, String email, String password, Integer age, Date birthday, Boolean isSpecialistUser, String cvUrl, List<String> roles) {
}
