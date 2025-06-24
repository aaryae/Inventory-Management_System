package com.example.inventorymanagementsystem.service.security;


import com.example.inventorymanagementsystem.dtos.request.PasswordResetRequest;
import com.example.inventorymanagementsystem.dtos.request.security.LoginRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RefreshTokenRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RegisterRequest;
import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {


    public ResponseEntity<ApiResponse> register(RegisterRequest request);

    public ResponseEntity<ApiResponse> login(LoginRequest loginRequest);

    public ResponseEntity<ApiResponse> refreshToken(RefreshTokenRequest request);

    public void sendResetCode(String email);
    public void verifyAndResetPassword(PasswordResetRequest request) ;

    }
