package com.mapu.backend.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {

    SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken(Authentication auth) {
        String jwt = Jwts.builder()
                .claim("IssuedAt", new Date())
                .claim("email", auth.getName())
                .claim("Expiration", new Date(new Date().getTime() + 86400000))
                .signWith(key)
                .compact();

        return jwt;
    }

    public String getEmailFromToken(String jwt) {
        jwt = jwt.substring(7);

        // Claims claims =
        // Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();

        Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();

        String email = String.valueOf(claims.get("email"));

        return email;
    }
}
