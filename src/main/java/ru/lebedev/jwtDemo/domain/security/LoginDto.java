package ru.lebedev.jwtDemo.domain.security;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
}
