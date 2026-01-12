package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // 🔐 Must be at least 32 characters for HS256
    private static final String SECRET_KEY =
            "mysecretkeymysecretkeymysecretkey12345";

    // ⏳ Token validity: 1 hour
    private static final long EXPIRATION_TIME = 60 * 60 * 1000;

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // ✅ Generate JWT token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256) // ✅ NOT deprecated
                .compact();
    }

    // ✅ Extract email from token
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // ✅ Validate token
    public boolean validateToken(String token, String email) {
        return email.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    // 🔎 Parse claims
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ⏱️ Expiry check
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
