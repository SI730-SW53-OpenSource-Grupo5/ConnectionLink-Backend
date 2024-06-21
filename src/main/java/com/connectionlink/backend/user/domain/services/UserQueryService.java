package com.connectionlink.backend.user.domain.services;

import com.connectionlink.backend.authuser.domain.model.aggregates.UserAuth;
import com.connectionlink.backend.authuser.domain.model.queries.GetUserByEmailQuery;
import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.connectionlink.backend.user.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllSpecialistUserQuery query);

    List<User> handle(GetAllPatientUserQuery query);

    Optional<User> handle(GetUserByIdQuery query);

    Optional<User> handle(GetUserByUsernameQuery query);

    Optional<User> handle(GetUserByEmailAndPassword query);

}
