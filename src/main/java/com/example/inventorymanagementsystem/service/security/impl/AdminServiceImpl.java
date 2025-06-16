package com.example.inventorymanagementsystem.service.security.impl;

import com.example.inventorymanagementsystem.dtos.response.PagedResponse;
import com.example.inventorymanagementsystem.dtos.response.security.UserResponse;
import com.example.inventorymanagementsystem.exception.DataNotFoundException;
import com.example.inventorymanagementsystem.model.User;
import com.example.inventorymanagementsystem.repository.securityRepo.UserRepository;
import com.example.inventorymanagementsystem.service.security.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
        private final UserRepository userRepository;
        private final MailSender mailSender;

    @Override
    public ResponseEntity<PagedResponse<UserResponse>> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        List<UserResponse> userData = users.getContent().stream()
                .map(user -> new UserResponse(user.getId(), user.getUsername(), user.getRole()))
                .toList();

        PagedResponse<UserResponse> response = new PagedResponse<>(
                userData,
                users.getNumber(),
                users.getSize(),
                users.getTotalElements(),
                users.getTotalPages(),
                users.isLast()
        );

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserResponse response = new UserResponse(user.getId(), user.getUsername(),user.getRole());
            return ResponseEntity.ok(response);
        } else {
            throw new DataNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public ResponseEntity<?> updateUserById(Long id, UserResponse userResponse){
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setUsername(userResponse.getUsername());
            userRepository.save(user);
            return ResponseEntity.ok("User updated successfully.");
        }
        else{
            throw new DataNotFoundException("User not found with id: " + id);
        }

    }

    @Override
    public ResponseEntity<?> deleteUserById(Long id) {
        Optional<User> userOptional =  userRepository.findById(id);

        if(userOptional.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted successfully.");
        }
        else{
            throw new DataNotFoundException("User not found with id: " + id);
        }
    }






}

