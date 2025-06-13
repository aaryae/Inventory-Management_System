package com.example.inventorymanagementsystem.dtos.request.security;


import com.example.inventorymanagementsystem.service.security.JwtService;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class RefreshTokenRequest {
    private String refreshToken;

}
