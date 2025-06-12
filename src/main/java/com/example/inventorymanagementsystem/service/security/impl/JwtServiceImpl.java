package com.example.inventorymanagementsystem.service.security.impl;

import java.security.Key;
import java.util.Date;

import com.example.inventorymanagementsystem.model.User;
import com.example.inventorymanagementsystem.service.security.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static ch.qos.logback.core.encoder.ByteArrayUtil.hexStringToByteArray;

@Service
public class JwtServiceImpl implements JwtService {

    
    private final String SECRET = "413F4428472B4B6250655368566D5970337336763979244226452948404D6351";
    private final Key jwtSigningKey = Keys.hmacShaKeyFor(hexStringToByteArray(SECRET));

    public String generateToken(User user,String  tokenType) {
        try{
            return Jwts.builder()
                    .setSubject(user.getUsername()) // main identity
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                    .claim("type", tokenType)
                    .signWith(jwtSigningKey, SignatureAlgorithm.HS256) // secret key & algorithm
                    .compact();
        }
        catch(Exception e){
            return "error: " + e.getMessage();
        }

    }
    @Override
    public String validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSigningKey)
                    .build()
                    .parseClaimsJws(token) // will throw if token is invalid
                    .getBody()
                    .getSubject(); // return username
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
    @Override
    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(jwtSigningKey, SignatureAlgorithm.HS256)
                .compact();
    }


    // function boolean is adscess toekkn

   // tokrn claims nikaler check garney

}
