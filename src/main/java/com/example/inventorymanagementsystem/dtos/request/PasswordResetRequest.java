package com.example.inventorymanagementsystem.dtos.request;


import lombok.Data;

@Data
public class PasswordResetRequest {
    private String email;
    private String code;
    private String newPassword;
}

