package com.connectionlink.backend.iam.application.internal.queryservices;

import com.connectionlink.backend.iam.domain.model.aggregates.User;
import com.connectionlink.backend.iam.domain.model.queries.*;
import com.connectionlink.backend.iam.domain.services.UserQueryService;
import com.connectionlink.backend.iam.infrastructure.persitence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;


    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetAllSpecialistUserQuery query) {
        return userRepository.findByIsSpecialistUserTrue();
    }

    @Override
    public List<User> handle(GetAllPatientUserQuery query) {
        return userRepository.findByIsSpecialistUserFalse();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.id());
    }

    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }

    @Override
    public Optional<User> handle(GetUserByEmailAndPassword query) {
        return userRepository.findByEmailAndPassword(query.email(), query.password());
    }
}
