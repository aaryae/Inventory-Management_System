package com.example.inventorymanagementsystem.service.security;

import com.example.inventorymanagementsystem.dtos.response.PagedResponse;
import com.example.inventorymanagementsystem.dtos.response.security.UserResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    public ResponseEntity<PagedResponse<UserResponse>> getAllUsers(Pageable page);

    public ResponseEntity<?> getUserById(Long id);

    public ResponseEntity<?> updateUserById(Long id, UserResponse userResponse);

    public ResponseEntity<?> deleteUserById(Long id);


}