package com.connectionlink.backend.iam.infrastructure.persitence.jpa;

import com.connectionlink.backend.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByIsSpecialistUserTrue();

    List<User> findByIsSpecialistUserFalse();

    Optional<User> findById(Long id);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmailAndPassword(String email, String password);

}