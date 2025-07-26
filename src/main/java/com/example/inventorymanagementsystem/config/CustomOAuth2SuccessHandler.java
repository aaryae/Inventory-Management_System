package com.example.inventorymanagementsystem.security.handlers;

import com.example.inventorymanagementsystem.service.security.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    public CustomOAuth2SuccessHandler(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OidcUser oidcUser = (OidcUser) oauthToken.getPrincipal();

        String email = oidcUser.getEmail();
        String username = oidcUser.getPreferredUsername();

        String accessToken = jwtService.buildToken(email, 1000 * 60 * 60 * 10, "access");

        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(
                Map.of("token", accessToken)
        ));
    }
}