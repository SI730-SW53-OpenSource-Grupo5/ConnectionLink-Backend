package com.connectionlink.backend.authuser.domain.services;

import com.connectionlink.backend.authuser.domain.model.aggregates.UserAuth;
import com.connectionlink.backend.authuser.domain.model.queries.AuthUserQuery;
import com.connectionlink.backend.authuser.domain.model.queries.GetUserByEmailQuery;

import java.util.Optional;

public interface UserAuthQueryService {

    Optional<UserAuth> handle(GetUserByEmailQuery query);
    boolean handle(AuthUserQuery query);

}
