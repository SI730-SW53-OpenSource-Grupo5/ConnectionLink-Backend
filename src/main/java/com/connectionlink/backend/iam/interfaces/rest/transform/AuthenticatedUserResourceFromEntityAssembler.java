package com.connectionlink.backend.iam.interfaces.rest.transform;

import com.connectionlink.backend.iam.domain.model.aggregates.User;
import com.connectionlink.backend.iam.interfaces.rest.resources.AuthenticationUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticationUserResource toResourceFromEntity(User entity, String token) {
        return new AuthenticationUserResource(entity.getId(), entity.getFullName(), entity.getUsername(), entity.getDescription(), entity.getProfileImageUrl(), entity.getBannerImageUrl(), entity.getEmail(), entity.getAge(), entity.getBirthday(), entity.getIsSpecialistUser(), entity.getCvUrl(), token);
    }
}
