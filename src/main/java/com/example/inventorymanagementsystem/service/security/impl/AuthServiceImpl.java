package com.example.inventorymanagementsystem.service.security.impl;

import com.example.inventorymanagementsystem.dtos.request.security.LoginRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RefreshTokenRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RegisterRequest;
import com.example.inventorymanagementsystem.dtos.response.security.LoginResponse;
import com.example.inventorymanagementsystem.model.User;
import com.example.inventorymanagementsystem.repository.securityRepo.UserRepository;
import com.example.inventorymanagementsystem.service.security.AuthService;
import com.example.inventorymanagementsystem.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists with this username.");
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
    public ResponseEntity<String> login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }

        return ResponseEntity.ok("Login successful!");
    }


    @Override
    public ResponseEntity<?> refreshToken( RefreshTokenRequest request) {
        String username = jwtService.validateToken(request.getRefreshToken());
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String newAccessToken = jwtService.generateToken(userDetails);
        String newRefreshToken = jwtService.generateRefreshToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(newAccessToken, newRefreshToken));
    }



}
