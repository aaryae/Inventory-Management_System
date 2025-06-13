package com.example.inventorymanagementsystem.dtos.response.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String role;
}
