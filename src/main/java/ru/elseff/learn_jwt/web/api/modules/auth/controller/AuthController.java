package ru.elseff.learn_jwt.web.api.modules.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elseff.learn_jwt.persistence.User;
import ru.elseff.learn_jwt.web.api.modules.auth.dto.AuthRequest;
import ru.elseff.learn_jwt.web.api.modules.auth.dto.AuthResponse;
import ru.elseff.learn_jwt.web.api.modules.auth.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }

}
