package com.connectionlink.backend.authuser.infraestructure.persistence.jpa;

import com.connectionlink.backend.authuser.domain.model.aggregates.UserAuth;
import com.connectionlink.backend.authuser.domain.model.valueobjects.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

    boolean existsById(Long id);

    Optional<UserAuth> findUserAuthByEmail(Email email);

    List<UserAuth> findAll();

}
