package org.example.authentication;

import static org.junit.Assert.*;
import org.junit.Test;

public class UserAuthenticationServiceTest {
    @Test
    public void testSuccessfulAuthentication() {
        UserAuthenticationService authService = new UserAuthenticationService();

        // Replace these with valid credentials
        String validUsername = "admin";
        String validPassword = "adminpassword";

        // Test successful authentication
        String jwtToken = authService.authenticateAndGenerateToken(validUsername, validPassword);
        assertNotNull(jwtToken);

        // Decode token and retrieve user role
        String userRole = authService.decodeTokenAndGetRole(jwtToken);

        // Assert that user role matches the expected role
        assertEquals("admin", userRole);
    }

    @Test
    public void testFailedAuthentication() {
        UserAuthenticationService authService = new UserAuthenticationService();

        // Replace these with invalid credentials
        String invalidUsername = "nonexistentuser";
        String invalidPassword = "invalidpassword";

        // Test failed authentication
        String jwtToken = authService.authenticateAndGenerateToken(invalidUsername, invalidPassword);
        assertNull(jwtToken);
    }
}