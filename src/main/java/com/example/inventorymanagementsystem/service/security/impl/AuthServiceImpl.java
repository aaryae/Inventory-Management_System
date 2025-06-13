package com.example.inventorymanagementsystem.service.security.impl;

import com.example.inventorymanagementsystem.dtos.request.security.LoginRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RefreshTokenRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RegisterRequest;
import com.example.inventorymanagementsystem.model.User;
import com.example.inventorymanagementsystem.repository.securityRepo.UserRepository;
import com.example.inventorymanagementsystem.service.security.AuthService;
import com.example.inventorymanagementsystem.service.security.JwtService;
import com.example.inventorymanagementsystem.exception.DuplicateResourceException;
import com.example.inventorymanagementsystem.exception.ResourceNotFoundExceptionHandler;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            throw new DuplicateResourceException("User already exists with username " + request.getUsername());
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                          .build();
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully.");
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler("User", "username", loginRequest.getUsername()));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new ResourceNotFoundExceptionHandler("User", "credentials", "Invalid username or password");
        }
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("refreshToken", token);
        response.put("accessToken", refreshToken);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> refreshToken(RefreshTokenRequest request) {
        try {
            String username = jwtService.validateToken(request.getRefreshToken());

            if (username.startsWith("error:")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
            }

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundExceptionHandler("User", "username", username));

            String newAccessToken = jwtService.generateRefreshToken(user);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", newAccessToken);

            return ResponseEntity.ok(tokens);
        } catch (ResourceNotFoundExceptionHandler ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (JwtException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token validation failed: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + ex.getMessage());
        }
    }


}









