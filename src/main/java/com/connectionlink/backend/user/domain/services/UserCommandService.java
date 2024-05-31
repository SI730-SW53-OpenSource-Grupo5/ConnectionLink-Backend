package com.connectionlink.backend.user.domain.services;

import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.connectionlink.backend.user.domain.model.commands.CreateUserCommand;
import com.connectionlink.backend.user.domain.model.commands.UpdateUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);
    Optional<User> handle(UpdateUserCommand command, Long id);
}
