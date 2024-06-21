package com.connectionlink.backend.authuser.application.internal.commandservices;

import com.connectionlink.backend.authuser.domain.model.aggregates.UserAuth;
import com.connectionlink.backend.authuser.domain.model.commands.CreateUserAuthCommand;
import com.connectionlink.backend.authuser.domain.services.UserAuthCommandService;
import com.connectionlink.backend.authuser.infraestructure.persistence.jpa.UserAuthRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthCommandServiceImpl implements UserAuthCommandService {

    private final UserAuthRepository userAuthRepository;

    public UserAuthCommandServiceImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public Optional<UserAuth> handle(CreateUserAuthCommand command) {

        System.out.println("UserAuthCommandServiceImpl: Creating user auth");

        var userAuth = new UserAuth(command);

        System.out.println("UserAuthCommandServiceImpl: User auth created");

        var createdUserAuth = userAuthRepository.save(userAuth);

        System.out.println("UserAuthCommandServiceImpl: User auth saved");

        return Optional.of(createdUserAuth);
    }

}
