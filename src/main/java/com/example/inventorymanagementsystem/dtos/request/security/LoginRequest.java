package com.example.inventorymanagementsystem.dtos.request.security;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginRequest {
    private String email;
    private String username;
    private String password;
}
