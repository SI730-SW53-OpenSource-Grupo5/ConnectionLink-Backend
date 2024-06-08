package com.connectionlink.backend.authuser.domain.services;

import com.connectionlink.backend.authuser.domain.model.aggregates.UserAuth;
import com.connectionlink.backend.authuser.domain.model.commands.CreateUserAuthCommand;

import java.util.Optional;

public interface UserAuthCommandService {

    Optional<UserAuth> handle(CreateUserAuthCommand command);

}
