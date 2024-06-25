package com.connectionlink.backend.iam.infrastructure.hashing.bcrypt;

import com.connectionlink.backend.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
