package com.example.inventorymanagementsystem.service.security;

import com.example.inventorymanagementsystem.model.User;

public interface JwtService {
    String generateToken(User user);
    String generateRefreshToken(User user);
    String validateToken(String token);

}
