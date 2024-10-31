package com.avocat.avocat.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private String SECRET_KEY = "your_secret_key";  // Replace with a secure key

    // Generate a JWT token for a given username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiry
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Extract the username from a token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Check if the token has expired
    public Boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Parse claims (data) from the token
    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
