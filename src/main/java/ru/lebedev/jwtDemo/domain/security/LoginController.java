package ru.lebedev.jwtDemo.domain.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lebedev.jwtDemo.domain.user.User;
import ru.lebedev.jwtDemo.domain.user.UserRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto id){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(id.getUsername(), id.getPassword()));
            User user = userRepository.findByUsername(id.getUsername()).orElseThrow(()-> new UsernameNotFoundException("User not found"));
            String token  = jwtTokenProvider.generateToken(user);
            Map<Object, Object> response = new HashMap<>();
            response.put("username", id.getUsername());
            response.put("token", token);

            return ResponseEntity.ok(response);
        }catch (AuthenticationException e){
            return new ResponseEntity<>("Invalid username/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LoginDto id){
        return null;
    }
}
