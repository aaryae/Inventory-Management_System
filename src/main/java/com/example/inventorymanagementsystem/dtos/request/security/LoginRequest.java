package com.example.inventorymanagementsystem.dtos.request.security;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder
public record  LoginRequest(@NotNull String email,@NotNull String password) {
}
