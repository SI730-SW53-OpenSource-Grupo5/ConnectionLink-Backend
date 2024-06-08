package com.connectionlink.backend.authuser.interfaces.rest.transform;

import com.connectionlink.backend.authuser.domain.model.aggregates.UserAuth;
import com.connectionlink.backend.authuser.interfaces.rest.resources.UserAuthResource;

public class UserAuthResourceFromEntityAssembler {

    public static UserAuthResource toResourceFromEntity(UserAuth entity) {

            return new UserAuthResource(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole()
            );
    }

}
