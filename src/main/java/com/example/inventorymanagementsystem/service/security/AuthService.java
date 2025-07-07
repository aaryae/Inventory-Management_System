package com.example.inventorymanagementsystem.service.security;


import com.example.inventorymanagementsystem.dtos.request.PasswordResetRequest;
import com.example.inventorymanagementsystem.dtos.request.security.LoginRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RefreshTokenRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RegisterRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AuthService {
    void register(RegisterRequest request);

    Map<String, String> login(LoginRequest loginRequest);

    Map<String, String> refreshToken(RefreshTokenRequest request);

    void sendResetCode(String email);

    void verifyAndResetPassword(PasswordResetRequest request);
}

