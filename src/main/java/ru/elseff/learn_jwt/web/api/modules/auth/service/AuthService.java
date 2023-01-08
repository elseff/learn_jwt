package ru.elseff.learn_jwt.web.api.modules.auth.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.elseff.learn_jwt.persistence.User;
import ru.elseff.learn_jwt.security.JwtProvider;
import ru.elseff.learn_jwt.web.api.modules.auth.dto.AuthRequest;
import ru.elseff.learn_jwt.web.api.modules.auth.dto.AuthResponse;
import ru.elseff.learn_jwt.web.api.modules.user.service.UserServiceImpl;

@Slf4j
@Service
public class AuthService {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse register(@NonNull User user) {
        String username = user.getUsername();
        if (userService.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with username: " + username + " already exists");
        }
        User savedUser = userService.addUser(user);
        log.info("user {} has been successfully registered", savedUser.getUsername());
        String token = jwtProvider.generateToken(username);

        return new AuthResponse(savedUser.getUsername(), token);

    }

    public AuthResponse login(@NonNull AuthRequest authRequest) {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        User userFromDb = userService.getByUsername(username);
        if (userFromDb != null) {
            if (passwordEncoder.matches(password, userFromDb.getPassword())) {
                String token = jwtProvider.generateToken(username);
                return new AuthResponse(username, token);
            } else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect password!");
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with username: " + username + " is not found");
    }
}
