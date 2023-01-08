package ru.elseff.learn_jwt.web.api.modules.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {

    private final String username;
    private final String token;
}
