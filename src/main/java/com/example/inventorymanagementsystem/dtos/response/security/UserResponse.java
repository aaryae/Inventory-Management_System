package com.example.inventorymanagementsystem.dtos.response.security;

import com.example.inventorymanagementsystem.helper.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    Role role;
}
