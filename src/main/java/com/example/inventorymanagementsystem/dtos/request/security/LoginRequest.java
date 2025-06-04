package com.example.inventorymanagementsystem.dtos.request.security;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;
}
