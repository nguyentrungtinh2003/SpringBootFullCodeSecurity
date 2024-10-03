package com.TrungTinhFullStack.SpringBootFullCodeSecurity.Service.Jwt;

import io.jsonwebtoken.*;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class JwtUtils {

    private final String jwtSecret = "6749846554756555647383856554385648547565647565746577";

    private final int jwtExpiration = 86400000;

    public String generateJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();

    }

    public String getUsernameFromJwtToken (String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String token) {
        try{
           Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

}
