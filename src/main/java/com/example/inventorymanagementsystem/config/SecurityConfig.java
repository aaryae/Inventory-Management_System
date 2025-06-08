package com.example.inventorymanagementsystem.config;

import com.example.inventorymanagementsystem.security.filters.JwtAuthenticationFilter;
import com.example.inventorymanagementsystem.service.security.JwtService;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@NoArgsConstructor(force = true)
public class SecurityConfig  {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;




    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http ,  AuthenticationManager authenticationManager,
                                                    JwtService jwtService ) throws Exception {


    JwtAuthenticationFilter jwtAuthFilter = new JwtAuthenticationFilter( jwtService,authenticationManager);



        http.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/register").permitAll()
                    .requestMatchers("/api/login").permitAll()
                    .anyRequest().authenticated())
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(csrf -> csrf.disable())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}


}
