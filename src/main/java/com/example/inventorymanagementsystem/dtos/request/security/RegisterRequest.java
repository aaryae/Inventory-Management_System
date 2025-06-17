package com.example.inventorymanagementsystem.dtos.request.security;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String email;
    private String username;
    private String password;
}
