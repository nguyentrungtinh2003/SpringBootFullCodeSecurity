package com.TrungTinhFullStack.SpringBootFullCodeSecurity.Controller;

import com.TrungTinhFullStack.SpringBootFullCodeSecurity.Entity.User;
import com.TrungTinhFullStack.SpringBootFullCodeSecurity.Repository.UserRepository;
import com.TrungTinhFullStack.SpringBootFullCodeSecurity.Service.Jwt.JwtUtils;
import com.TrungTinhFullStack.SpringBootFullCodeSecurity.Service.Jwt.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(user.getUsername());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

       User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(webSecurityConfig.passwordEncoder().encode(user.getPassword()));
        user1.setRole("ROLE_USER");

        userRepository.save(user1);

        return ResponseEntity.ok("User registered successfully!");
    }
}
