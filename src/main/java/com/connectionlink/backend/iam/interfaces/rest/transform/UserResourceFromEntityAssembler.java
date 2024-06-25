package com.connectionlink.backend.iam.interfaces.rest.transform;

import com.connectionlink.backend.iam.domain.model.aggregates.User;
import com.connectionlink.backend.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(entity.getId(), entity.getFullName(), entity.getUsername(), entity.getDescription(), entity.getProfileImageUrl(), entity.getBannerImageUrl(), entity.getEmail(), entity.getAge(), entity.getBirthday(), entity.getIsSpecialistUser(), entity.getCvUrl());
    }
}
