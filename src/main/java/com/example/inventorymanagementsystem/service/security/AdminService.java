package com.example.inventorymanagementsystem.service.security;

import com.example.inventorymanagementsystem.dtos.response.PagedResponse;
import com.example.inventorymanagementsystem.dtos.response.security.UserResponse;
import com.example.inventorymanagementsystem.model.User;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface AdminService {

   public ResponseEntity<PagedResponse<UserResponse>>  getAllUsers(Pageable page);

   public ResponseEntity<?> getUserById(Long id);

   public ResponseEntity<?> updateUserById(Long id, UserResponse userResponse);

   public ResponseEntity<?> deleteUserById(Long id);


}