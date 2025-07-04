package com.example.inventorymanagementsystem.service.security;

import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import com.example.inventorymanagementsystem.dtos.response.PagedResponse;
import com.example.inventorymanagementsystem.dtos.response.security.UserResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    public ResponseEntity<ApiResponse> getAllUsers();

    public ResponseEntity<ApiResponse> getUserById(Long id);

    public ResponseEntity<ApiResponse> updateUserById(Long id, UserResponse userResponse);

    public ResponseEntity<ApiResponse> deleteUserById(Long id);


}