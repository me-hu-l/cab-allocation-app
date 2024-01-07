package org.example.authentication;
import io.jsonwebtoken.*;

import java.util.Date;


public class JwtUtil {

    public static String generateToken(String username, String role, String SECRET_KEY) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims decodeToken(String token, String SECRET_KEY) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw e;  // Re-throw the ExpiredJwtException explicitly
        }
    }
}