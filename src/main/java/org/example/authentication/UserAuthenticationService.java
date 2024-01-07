package org.example.authentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;


public class UserAuthenticationService {
    private static final String SECRET_KEY = "S9kWfgfbSi6VGGo+7MqAYFpOEljuIESpyn4nPQcLUFv+LDkO+eTSPQD+AinVdOor"; // Replace with a strong secret key

    private Map<String, String> userCredentials;

    public UserAuthenticationService() {
        // Initialize with hardcoded user credentials (for illustration purposes)
        userCredentials = new HashMap<>();
        userCredentials.put("admin", "adminpassword");
        userCredentials.put("employee", "employeepassword");
    }

    public String authenticateAndGenerateToken(String username, String password) {
        // Authenticate user (replace with your authentication logic)
        if (authenticateUser(username, password)) {
            // Generate JWT token using JwtUtil
            String role = getUserRole(username);
            return JwtUtil.generateToken(username, role, SECRET_KEY);
        }
        return null;
    }

    public String decodeTokenAndGetRole(String token) {
        // Decode token and retrieve user role using JwtUtil
        Claims claims = JwtUtil.decodeToken(token, SECRET_KEY);
        return (String) claims.get("role");
    }

    private boolean authenticateUser(String username, String password) {
        // Check if the username exists in the userCredentials map
        if (userCredentials.containsKey(username)) {
            // Retrieve the stored password for the given username
            String storedPassword = userCredentials.get(username);

            // Compare the stored password with the provided password
            return storedPassword.equals(password);
        }

        // Username not found, authentication fails
        return false;
    }

    private String getUserRole(String username) {
        // For simplicity, assume roles are hardcoded based on username
        if ("admin".equals(username)) {
            return "admin";
        } else if ("employee".equals(username)) {
            return "employee";
        }

        // Default role if username not recognized
        return "unknown";
    }
}