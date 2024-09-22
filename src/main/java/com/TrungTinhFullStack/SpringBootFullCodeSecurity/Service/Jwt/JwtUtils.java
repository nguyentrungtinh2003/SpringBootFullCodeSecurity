package com.TrungTinhFullStack.SpringBootFullCodeSecurity.Service.Jwt;

import io.jsonwebtoken.*;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class JwtUtils {

    private String JwtSecret = "465736458576485868659574649606854643543546575858";

    private int JwtExpiration = 86400000;

    public String generateJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + JwtExpiration))
                .signWith(SignatureAlgorithm.HS512,JwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken (String token) {
        return Jwts.parser().setSigningKey(JwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String token) {
        try{
            Jwts.parser().setSigningKey(JwtSecret).parseClaimsJws(token);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

}
