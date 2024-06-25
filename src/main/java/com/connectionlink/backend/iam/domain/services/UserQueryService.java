package com.connectionlink.backend.iam.domain.services;

import com.connectionlink.backend.iam.domain.model.aggregates.User;
import com.connectionlink.backend.iam.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllSpecialistUserQuery query);

    List<User> handle(GetAllPatientUserQuery query);

    Optional<User> handle(GetUserByIdQuery query);

    Optional<User> handle(GetUserByUsernameQuery query);

    Optional<User> handle(GetUserByEmailAndPassword query);

}
