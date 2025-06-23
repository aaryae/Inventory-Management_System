package com.example.inventorymanagementsystem.service.security;


import com.example.inventorymanagementsystem.dtos.PasswordDTO;
import com.example.inventorymanagementsystem.dtos.request.PasswordResetRequest;
import com.example.inventorymanagementsystem.dtos.request.security.LoginRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RefreshTokenRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RegisterRequest;
import com.example.inventorymanagementsystem.dtos.response.security.LoginResponse;
import com.example.inventorymanagementsystem.model.User;
import jakarta.mail.MessagingException;
import org.apache.coyote.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public interface AuthService {


    public ResponseEntity<?> register(RegisterRequest request);

    public ResponseEntity<?> login(LoginRequest loginRequest);

    public ResponseEntity<?> refreshToken(RefreshTokenRequest request);

    public void sendResetCode(String email);
    public void verifyAndResetPassword(PasswordResetRequest request) ;

    }
