package org.example.authentication;

import io.jsonwebtoken.Claims;
import org.junit.Test;

import static org.junit.Assert.*;

public class JwtUtilTest {

    @Test
    public void testGenerateTokenAndDecode() {
        // Arrange
        String username = "john.doe";
        String role = "user";
        String SECRET_KEY = "S9kWfgfbSi6VGGo+7MqAYFpOEljuIESpyn4nPQcLUFv+LDkO+eTSPQD+AinVdOor";
        // Act
        String token = JwtUtil.generateToken(username, role, SECRET_KEY);
        assertNotNull(token);

        // Assert
        Claims claims = JwtUtil.decodeToken(token, SECRET_KEY);
        assertEquals(username, claims.getSubject());
        assertEquals(role, claims.get("role"));
    }

    @Test
    public void testExpiredToken() {

        // Simulate token expiration by setting the current time in the past
        String SECRET_KEY = "iIjhrgWI2ls9Bo/Jv0Ws/Ve5eSuGN+W4Ed9x8ZDnDEeCRW9yM+jPahQ11HhqcDG7";  // Change secret key to invalidate the signature

        // Arrange
        String expiredToken = JwtUtil.generateToken("user1", "admin", SECRET_KEY);


        try {
            Thread.sleep(2000);  // Sleep for 1 second to make the token expire
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Act & Assert
        assertThrows(io.jsonwebtoken.ExpiredJwtException.class, () -> JwtUtil.decodeToken(expiredToken, SECRET_KEY));
    }

}