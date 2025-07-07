package com.example.inventorymanagementsystem.service.security;

import com.example.inventorymanagementsystem.dtos.response.security.UserResponse;
import com.example.inventorymanagementsystem.model.User;

import java.util.List;

public interface AdminService {
    List<User> getAllUsers();

    UserResponse getUserById(Long id);

    void updateUserById(Long id, UserResponse userResponse);

    void deleteUserById(Long id);
}
