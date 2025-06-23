package com.example.inventorymanagementsystem.service.security.impl;

import com.example.inventorymanagementsystem.dtos.request.PasswordResetRequest;
import com.example.inventorymanagementsystem.dtos.request.security.LoginRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RefreshTokenRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RegisterRequest;
import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import com.example.inventorymanagementsystem.exception.DataNotFoundException;
import com.example.inventorymanagementsystem.helper.Role;
import com.example.inventorymanagementsystem.model.User;
import com.example.inventorymanagementsystem.repository.securityRepo.UserRepository;
import com.example.inventorymanagementsystem.service.MailService;
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

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final MailService mailService;

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new DuplicateResourceException("User already exists with username " + request.getEmail());
        }
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .passwordLastUpdated(LocalDateTime.now())
                .role(Role.USER)
                          .build();
        userRepository.save(user);
        mailService.sendWelcomeMail(user);
        return ResponseEntity.ok().body(new ApiResponse("User registered successfully.", true));
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler("User", "username", loginRequest.email()));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new ResourceNotFoundExceptionHandler("User", "credentials", "Invalid username or password");
        }
        if (user.getPasswordLastUpdated().isBefore(LocalDateTime.now().minusYears(1))){
            mailService.sendPasswordAboutToExpire(user);
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime passwordLastUpdated = user.getPasswordLastUpdated();

        if (passwordLastUpdated.isBefore(now.minusYears(1))) {
            mailService.sendPasswordReset(user);
            throw new DataNotFoundException("Your password has expired. A reset link has been sent to your email.");
        }

        if (passwordLastUpdated.isBefore(now.minusMonths(11))) {
            mailService.sendPasswordAboutToExpire(user);
        }


        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);


        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("accessToken", token);
        response.put("refreshToken", refreshToken);

        return ResponseEntity.ok(response);
    }


    @Override
    public void sendResetCode(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler("User", "email", email));

        mailService.sendPasswordReset(user);
    }


    @Override
    public void verifyAndResetPassword(PasswordResetRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler("User", "email", request.getEmail()));

        boolean valid = mailService.verify(request.getCode(), user);

        if (!valid) {
            throw new IllegalArgumentException("Invalid or expired code.");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }




    @Override
    public ResponseEntity<?> refreshToken(RefreshTokenRequest request) {
        try {
            String email = jwtService.validateToken(request.getAccessToken());

            if (email.startsWith("error:")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
            }

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundExceptionHandler("User", "email", email));

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









