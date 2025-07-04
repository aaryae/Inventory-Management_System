package com.example.inventorymanagementsystem.dtos.request.security;


import jakarta.validation.constraints.NotNull;

public record RegisterRequest(@NotNull String username,@NotNull String email,@NotNull String password) {

}
