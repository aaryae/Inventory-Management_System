package com.example.inventorymanagementsystem.dtos.request;


import jakarta.validation.constraints.NotNull;

public record PasswordResetRequest(@NotNull String email,@NotNull String code,@NotNull String newPassword) {
}

