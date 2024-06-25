package com.connectionlink.backend.iam.infrastructure.persitence.jpa;

import com.connectionlink.backend.iam.domain.model.entities.Role;
import com.connectionlink.backend.iam.domain.model.valueobjects.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);

    boolean existsByName(Roles name);
}
