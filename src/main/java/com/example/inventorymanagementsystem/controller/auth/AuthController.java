package com.example.inventorymanagementsystem.controller.auth;

import com.example.inventorymanagementsystem.dtos.request.security.LoginRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RefreshTokenRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RegisterRequest;
import com.example.inventorymanagementsystem.service.MailService;
import com.example.inventorymanagementsystem.service.security.AuthService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final MailService mailService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return authService.register(request);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest) throws MessagingException, UnsupportedEncodingException {
        return authService.forgotPassword(loginRequest, getSiteURL(httpServletRequest));
    }
    private String getSiteURL(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }


    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String code) {
        boolean verified = mailService.verify(code);
        if (verified) {
            return ResponseEntity.ok("Your email has been successfully verified!");
        } else {
            return ResponseEntity.badRequest().body("Verification failed: Invalid or expired code.");
        }
    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest token) {
        return authService.refreshToken(token);
    }
}
