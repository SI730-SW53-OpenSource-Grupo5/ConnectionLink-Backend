package com.connectionlink.backend.authuser.domain.model.queries;

import com.connectionlink.backend.authuser.domain.model.valueobjects.Email;

public record GetUserByEmailQuery(Email email) {
}
