package com.connectionlink.backend.iam.domain.services;


import com.connectionlink.backend.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
