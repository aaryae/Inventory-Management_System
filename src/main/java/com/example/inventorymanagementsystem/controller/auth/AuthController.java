package com.example.inventorymanagementsystem.controller.auth;

import com.example.inventorymanagementsystem.dtos.request.PasswordResetRequest;
import com.example.inventorymanagementsystem.dtos.request.security.LoginRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RefreshTokenRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RegisterRequest;
import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import com.example.inventorymanagementsystem.service.MailService;
import com.example.inventorymanagementsystem.service.security.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

//@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
@Tag(name = "Authentication", description = "Authentication API")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    @Operation(summary = "Register user")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return authService.register(request);

    }

    @PostMapping("/login")
    @Operation(summary = "Login user")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }


    @PostMapping("/request-reset")
    @Operation(summary = "Request password reset code")
    public ResponseEntity<?> requestReset(@RequestBody LoginRequest loginRequest) {
        authService.sendResetCode(loginRequest.getEmail());
        return ResponseEntity.ok().body(new ApiResponse( "Password reset code sent to your email.",true) );
    }

    @PostMapping("/verify-reset")
    @Operation(summary = "Verify password reset code and reset password")
    public ResponseEntity<?> verifyReset(@RequestBody PasswordResetRequest request) {
        authService.verifyAndResetPassword(request);
        return ResponseEntity.ok().body(new ApiResponse( "Password reset successfully.",true) );
    }


    @PostMapping("/refresh")
    @Operation(summary = "Generates refresh token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest token) {
        return authService.refreshToken(token);
    }
}
