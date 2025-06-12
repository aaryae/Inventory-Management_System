package com.example.inventorymanagementsystem.service.security;

import com.example.inventorymanagementsystem.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(User user,String  tokenType);
    String generateRefreshToken(User user);
    String validateToken(String token);

}
