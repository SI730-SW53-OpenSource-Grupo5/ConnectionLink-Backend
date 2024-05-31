package com.connectionlink.backend.user.application.internal.commandservices;

import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.connectionlink.backend.user.domain.model.commands.CreateUserCommand;
import com.connectionlink.backend.user.domain.model.commands.UpdateUserCommand;
import com.connectionlink.backend.user.domain.services.UserCommandService;
import com.connectionlink.backend.user.infrastructure.persitence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * Handles the CreateUserCommand command.
     *
     * @param command - the CreateUserCommand command
     * @return an Optional of User
     */
    @Override
    public Optional<User> handle(CreateUserCommand command) {
        if(userRepository.existsByUsername(command.username())) {
            throw  new IllegalArgumentException("User with same username already exists");
        }

        User user = new User(command);

        var createdUser = userRepository.save(user);
        return  Optional.of(createdUser);
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command, Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setFullName(command.fullName());
        user.setDescription(command.description());
        user.setProfileImageUrl(command.profileImageUrl());
        user.setBannerImageUrl(command.bannerImageUrl());
        user.setAge(command.age());
        user.setBirthday(command.birthday());
        user.setCvUrl(command.cvUrl());

        var userUpdated = this.userRepository.save(user);

        return Optional.of(userUpdated);
    }
}
