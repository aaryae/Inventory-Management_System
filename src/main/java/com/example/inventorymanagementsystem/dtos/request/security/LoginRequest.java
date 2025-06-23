package com.example.inventorymanagementsystem.dtos.request.security;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
public record  LoginRequest(String email, String password) {
}
