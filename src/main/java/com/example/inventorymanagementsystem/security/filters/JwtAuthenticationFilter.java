package com.example.inventorymanagementsystem.security.filters;

import com.example.inventorymanagementsystem.dtos.request.security.LoginRequest;
import com.example.inventorymanagementsystem.service.security.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.AuthenticationManager;


import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    public final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(JwtService jwtService, AuthenticationManager authenticationManager){
        this.jwtService=jwtService;
        this.authenticationManager=authenticationManager;
    }

    @Override
    protected void doFilterInternal(
             HttpServletRequest request,
             HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        if(!request.getServletPath().equals("/api/login")){
            filterChain.doFilter(request, response);
            return ;
        }

        ObjectMapper objectMapper= new ObjectMapper();
        LoginRequest loginRequest= objectMapper.readValue(request.getInputStream(), LoginRequest.class);
        UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());

        Authentication authResult= authenticationManager.authenticate(authToken);

        if (authResult.isAuthenticated()) {
            String token = jwtService.generateToken((UserDetails) authResult.getPrincipal());
            response.setHeader("Authorization", "Bearer " + token);
        }
    }

}
