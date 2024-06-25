package com.connectionlink.backend.iam.domain.model.queries;

import com.connectionlink.backend.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
