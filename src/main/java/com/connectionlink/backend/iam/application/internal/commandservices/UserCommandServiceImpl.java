package com.connectionlink.backend.iam.application.internal.commandservices;

import com.connectionlink.backend.iam.application.internal.outboundservices.hashing.HashingService;
import com.connectionlink.backend.iam.application.internal.outboundservices.tokens.TokenService;
import com.connectionlink.backend.iam.domain.model.aggregates.User;
import com.connectionlink.backend.iam.domain.model.commands.CreateUserCommand;
import com.connectionlink.backend.iam.domain.model.commands.SignInCommand;
import com.connectionlink.backend.iam.domain.model.commands.SignUpCommand;
import com.connectionlink.backend.iam.domain.model.commands.UpdateUserCommand;
import com.connectionlink.backend.iam.domain.model.entities.Role;
import com.connectionlink.backend.iam.domain.services.UserCommandService;
import com.connectionlink.backend.iam.infrastructure.persitence.jpa.RoleRepository;
import com.connectionlink.backend.iam.infrastructure.persitence.jpa.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
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

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        User user = userRepository.findByUsername(command.username()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(!hashingService.matches(command.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid Password");
        }

        var token = tokenService.generateToken(user.getUsername());

        return Optional.of(ImmutablePair.of(user, token));
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if(userRepository.existsByUsername(command.username())) {
            throw  new IllegalArgumentException("User with same username already exists");
        }

        CreateUserCommand newUser = new CreateUserCommand(command.fullName(),command.username(), command.description(), command.profileImageUrl(), command.bannerImageUrl(), command.email(), hashingService.encode(command.password()), command.age(), command.birthday(), command.isSpecialistUser(), command.cvUrl());

        List<Role> roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName()).orElseThrow(() -> new IllegalArgumentException("Role not found"))).toList();

        User user = new User(newUser, roles);

        var createdUser = userRepository.save(user);
        return  Optional.of(createdUser);
    }
}
