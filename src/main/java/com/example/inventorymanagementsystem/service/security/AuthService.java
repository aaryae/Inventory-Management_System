package com.example.inventorymanagementsystem.service.security;


import com.example.inventorymanagementsystem.dtos.request.security.LoginRequest;
import com.example.inventorymanagementsystem.dtos.request.security.RegisterRequest;
import com.example.inventorymanagementsystem.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {


    public ResponseEntity<?> register(RegisterRequest request);

    public ResponseEntity<?> login(LoginRequest loginRequest);

}
