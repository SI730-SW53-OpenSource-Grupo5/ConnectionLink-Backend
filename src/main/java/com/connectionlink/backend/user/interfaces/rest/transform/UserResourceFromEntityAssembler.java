package com.connectionlink.backend.user.interfaces.rest.transform;

import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.connectionlink.backend.user.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(entity.getId(), entity.getFullName(), entity.getUsername(), entity.getDescription(), entity.getProfileImageUrl(), entity.getBannerImageUrl(), entity.getEmail(), entity.getPassword(), entity.getAge(), entity.getBirthday(), entity.getIsSpecialistUser(), entity.getCvUrl());
    }
}
