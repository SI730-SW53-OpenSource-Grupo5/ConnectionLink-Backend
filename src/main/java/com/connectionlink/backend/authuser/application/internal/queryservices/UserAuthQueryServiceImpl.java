package com.connectionlink.backend.authuser.application.internal.queryservices;

import com.connectionlink.backend.authuser.domain.model.aggregates.UserAuth;
import com.connectionlink.backend.authuser.domain.model.queries.AuthUserQuery;
import com.connectionlink.backend.authuser.domain.model.queries.GetUserByEmailQuery;
import com.connectionlink.backend.authuser.domain.services.UserAuthQueryService;
import com.connectionlink.backend.authuser.infraestructure.persistence.jpa.UserAuthRepository;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserAuthQueryServiceImpl implements UserAuthQueryService {

    private final UserAuthRepository userAuthRepository;

    public UserAuthQueryServiceImpl(UserAuthRepository userAuthQueryRepository) {
        this.userAuthRepository = userAuthQueryRepository;
    }

    @Override
    public boolean handle(AuthUserQuery query) {
        try {
            Optional<UserAuth> userAuthOpt = userAuthRepository.findUserAuthByEmail(query.email());
            if (userAuthOpt.isPresent()) {
                UserAuth userAuth = userAuthOpt.get();
                return query.password().getPassword().equals(userAuth.getPassword());
            }
            return false;
        } catch (Exception e) {
            System.out.println("UserAuthCommandServiceImpl: Error while authenticating user" + e);
            return false;
        }
    }

    @Override
    public Optional<UserAuth> handle(GetUserByEmailQuery query) {
        try {
            return userAuthRepository.findUserAuthByEmail(query.email());
        } catch (Exception e) {
            throw new RuntimeException("Error while retrieving user", e);
        }
    }

}
