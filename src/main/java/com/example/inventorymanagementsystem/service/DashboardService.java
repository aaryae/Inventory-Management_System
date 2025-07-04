package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface DashboardService {
   public ResponseEntity<ApiResponse> getResourceCountByBrand();

    public ResponseEntity<ApiResponse>  getResourceCountByModel();

    public ResponseEntity<ApiResponse>  getResourceCountBySpecification();


    public ResponseEntity<ApiResponse>  getResourceCountByResourceTypeName();
}
