package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface SearchService {

    public ResponseEntity<ApiResponse> filterResources(String brand, String model, LocalDate purchaseDate, String specification);
}
