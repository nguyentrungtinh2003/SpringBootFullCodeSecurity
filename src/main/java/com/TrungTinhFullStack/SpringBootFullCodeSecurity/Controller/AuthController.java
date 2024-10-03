package com.TrungTinhFullStack.SpringBootFullCodeSecurity.Controller;

import com.TrungTinhFullStack.SpringBootFullCodeSecurity.Dto.ReqRes;
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
    public ResponseEntity<ReqRes> authenticateUser(@RequestBody User user) {
        User user1 = userRepository.findByUsername(user.getUsername());
        ReqRes reqRes = new ReqRes();
       Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
       );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(user.getUsername());

        reqRes.setId(user1.getId());
        reqRes.setUsername(user.getUsername());
        reqRes.setRole(user1.getRole());
        reqRes.setStatusCode(200L);
        reqRes.setMessage("Login success !");
        reqRes.setJwt(jwt);
        return ResponseEntity.ok(reqRes);
    }

    @PostMapping("/register")
    public ResponseEntity<ReqRes> registerUser(@RequestBody User user) {
        ReqRes reqRes = new ReqRes();
        if (userRepository.existsByUsername(user.getUsername())) {
            reqRes.setStatusCode(500L);
            reqRes.setMessage("Username is already taken with : " + user.getUsername());
            return ResponseEntity.internalServerError().body(reqRes);
        }

        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(webSecurityConfig.passwordEncoder().encode(user.getUsername()));
        user1.setRole("USER");

        userRepository.save(user1);

        reqRes.setId(user1.getId());
        reqRes.setUsername(user1.getUsername());
        reqRes.setRole(user1.getRole());
        reqRes.setStatusCode(200L);
        reqRes.setMessage("Register success !");

        return ResponseEntity.ok(reqRes);
    }
}
