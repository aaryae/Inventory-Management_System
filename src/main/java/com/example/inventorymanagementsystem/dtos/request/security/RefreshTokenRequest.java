package com.example.inventorymanagementsystem.dtos.request.security;


import lombok.Builder;


@Builder
public record RefreshTokenRequest(String accessToken) {

}
