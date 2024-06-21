package com.connectionlink.backend.authuser.interfaces.rest;

import com.connectionlink.backend.authuser.domain.model.aggregates.UserAuth;
import com.connectionlink.backend.authuser.domain.model.queries.AuthUserQuery;
import com.connectionlink.backend.authuser.domain.model.queries.GetUserByEmailQuery;
import com.connectionlink.backend.authuser.domain.model.valueobjects.Email;
import com.connectionlink.backend.authuser.domain.model.valueobjects.Password;
import com.connectionlink.backend.authuser.domain.services.UserAuthCommandService;
import com.connectionlink.backend.authuser.domain.services.UserAuthQueryService;
import com.connectionlink.backend.authuser.interfaces.rest.resources.CreateUserAuthResource;
import com.connectionlink.backend.authuser.interfaces.rest.resources.UserAuthResource;
import com.connectionlink.backend.authuser.interfaces.rest.transform.CreateUserAuthCommandFromResourceAssembler;
import com.connectionlink.backend.authuser.interfaces.rest.transform.UserAuthResourceFromEntityAssembler;
import com.connectionlink.backend.user.domain.model.aggregates.User;
import com.connectionlink.backend.user.domain.model.commands.CreateUserCommand;
import com.connectionlink.backend.user.domain.model.queries.GetUserByEmailAndPassword;
import com.connectionlink.backend.user.domain.services.UserCommandService;
import com.connectionlink.backend.user.domain.services.UserQueryService;
import com.connectionlink.backend.user.interfaces.rest.resources.UserResource;
import com.connectionlink.backend.user.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/auth", produces = APPLICATION_JSON_VALUE)
@Tag(name = "auth-controller", description = "Auth API")
public class AuthController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public AuthController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResource> loginUser(@RequestBody GetUserByEmailAndPassword command) {
        Optional<User> userOpt = userQueryService.handle(command);

        if(userOpt.isPresent()) {
            User user = userOpt.get();
            UserResource userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);
            return ResponseEntity.ok(userResource);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 Unauthorized
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserResource> registerUser(@RequestBody CreateUserCommand command) {
        Optional<User> userOpt = userCommandService.handle(command);
        return userOpt.map(source -> new ResponseEntity<>(UserResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    /*private final UserAuthCommandService authCommandService;
    private final UserAuthQueryService authQueryService;

    public AuthController(UserAuthCommandService authCommandService, UserAuthQueryService authQueryService) {
        this.authCommandService = authCommandService;
        this.authQueryService = authQueryService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserAuthResource> registerNewUser(@RequestBody CreateUserAuthResource resource) {

        // to verify if the user's email already exists in the database
        GetUserByEmailQuery query = new GetUserByEmailQuery(new Email(resource.email()));
        Optional<UserAuth> userAuthOpt = authQueryService.handle(query);

        // if user exists, return a bad request
        if (userAuthOpt.isPresent()) {
            System.out.println("Email already exist");
            return ResponseEntity.badRequest().build();
        } else {
            // if the user does not exist, create a new user
            Optional<UserAuth> user = authCommandService.handle(CreateUserAuthCommandFromResourceAssembler.toCommand(resource));
            return user.map(source -> new ResponseEntity<>(UserAuthResourceFromEntityAssembler.toResourceFromEntity(source), HttpStatus.CREATED))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }
    }

    @GetMapping("/login")
    public ResponseEntity<UserAuthResource> existsUser(String email, String password) {
        GetUserByEmailQuery getUserQuery = new GetUserByEmailQuery(new Email(email));
        Optional<UserAuth> userAuthOpt = authQueryService.handle(getUserQuery);

        if (userAuthOpt.isPresent()) {
            AuthUserQuery authUserQuery = new AuthUserQuery(new Email(email), new Password(password));
            boolean passwordMatches = authQueryService.handle(authUserQuery);

            if (passwordMatches) {
                UserAuth userAuth = userAuthOpt.get();
                UserAuthResource userAuthResource = UserAuthResourceFromEntityAssembler.toResourceFromEntity(userAuth);
                return ResponseEntity.ok(userAuthResource);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 Unauthorized
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }*/

}
