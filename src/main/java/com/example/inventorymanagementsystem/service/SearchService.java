package com.example.inventorymanagementsystem.service;

import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface SearchService {

    public ResponseEntity<?> filterResources(String brand, String model, LocalDate purchaseDate, String Speficication);
}
