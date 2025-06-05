package com.example.inventorymanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig  {


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

@Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/register").permitAll()
                    .requestMatchers("/api/login").permitAll()
                    .anyRequest().authenticated())
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(csrf -> csrf.disable());
    return http.build();
}


}
