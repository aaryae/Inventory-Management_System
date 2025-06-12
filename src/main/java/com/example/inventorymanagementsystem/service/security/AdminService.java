package com.example.inventorymanagementsystem.service.security;

import com.example.inventorymanagementsystem.dtos.response.PagedResponse;
import com.example.inventorymanagementsystem.dtos.response.security.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {

   public ResponseEntity<PagedResponse<UserResponse>>  getAllUsers(Pageable page);
}