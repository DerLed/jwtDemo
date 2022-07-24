package ru.lebedev.jwtDemo.domain.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto id){
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LoginDto id){
        return null;
    }
}
