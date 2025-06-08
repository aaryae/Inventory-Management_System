package com.example.inventorymanagementsystem.service.security.impl;

import java.security.Key;
import java.util.Date;

import com.example.inventorymanagementsystem.service.security.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static ch.qos.logback.core.encoder.ByteArrayUtil.hexStringToByteArray;

@Component
public class JwtServiceImpl implements JwtService {

    
    private final String SECRET = "413F4428472B4B6250655368566D5970337336763979244226452948404D6351";
    private final Key jwtSigningKey = Keys.hmacShaKeyFor(hexStringToByteArray(SECRET));

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // main identity
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(jwtSigningKey, SignatureAlgorithm.HS256) // secret key & algorithm
                .compact();
    }


}
