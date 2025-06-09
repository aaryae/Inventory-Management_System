package com.example.inventorymanagementsystem.service.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(UserDetails userDetails);
    String generateRefreshToken(UserDetails userDetails);
    String validateToken(String token);

}
