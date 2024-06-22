package com.connectionlink.backend.iam.domain.services;

import com.connectionlink.backend.iam.domain.model.aggregates.User;
import com.connectionlink.backend.iam.domain.model.commands.CreateUserCommand;
import com.connectionlink.backend.iam.domain.model.commands.SignInCommand;
import com.connectionlink.backend.iam.domain.model.commands.SignUpCommand;
import com.connectionlink.backend.iam.domain.model.commands.UpdateUserCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);
    Optional<User> handle(UpdateUserCommand command, Long id);
    Optional<ImmutablePair<User, String>>  handle(SignInCommand command);
    Optional<User> handle(SignUpCommand command);
}
