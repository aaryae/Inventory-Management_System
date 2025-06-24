package com.example.inventorymanagementsystem.dtos.request;



public record PasswordResetRequest(String email, String code, String newPassword) {
}

