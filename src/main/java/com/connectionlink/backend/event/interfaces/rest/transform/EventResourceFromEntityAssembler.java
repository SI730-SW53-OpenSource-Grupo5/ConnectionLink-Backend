package com.connectionlink.backend.event.interfaces.rest.transform;

import com.connectionlink.backend.category.interfaces.rest.resources.CategoryResource;
import com.connectionlink.backend.category.interfaces.rest.transform.CategoryResourceFromEntityAssembler;
import com.connectionlink.backend.event.domain.model.aggregates.Event;
import com.connectionlink.backend.event.interfaces.rest.resources.EventResource;
import com.connectionlink.backend.iam.interfaces.rest.resources.UserResource;
import com.connectionlink.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

import java.util.List;

public class EventResourceFromEntityAssembler {
    public static EventResource toResourceFromEntity(Event entity) {
        List<UserResource> users = entity.getUsers().stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        CategoryResource category = CategoryResourceFromEntityAssembler.toResourceFromEntity(entity.getCategory());
        UserResource specialist = UserResourceFromEntityAssembler.toResourceFromEntity(entity.getSpecialist());

        return new EventResource(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getProfileImageUrl(), entity.getBannerImageUrl(), entity.getDay(), category, users, specialist);
    }
}
