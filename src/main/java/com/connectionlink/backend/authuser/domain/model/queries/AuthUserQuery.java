package com.connectionlink.backend.authuser.domain.model.queries;

import com.connectionlink.backend.authuser.domain.model.valueobjects.Email;
import com.connectionlink.backend.authuser.domain.model.valueobjects.Password;

public record AuthUserQuery(Email email, Password password) {
}
