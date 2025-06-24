package com.example.inventorymanagementsystem.service.security.impl;

import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import com.example.inventorymanagementsystem.dtos.response.PagedResponse;
import com.example.inventorymanagementsystem.dtos.response.security.UserResponse;
import com.example.inventorymanagementsystem.exception.DataNotFoundException;
import com.example.inventorymanagementsystem.helper.MessageConstant;
import com.example.inventorymanagementsystem.model.User;
import com.example.inventorymanagementsystem.repository.security.UserRepository;
import com.example.inventorymanagementsystem.service.security.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
        private final UserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponse> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        List<UserResponse> userData = users.getContent().stream()
                .map(user -> new UserResponse(user.getId(), user.getEmail(), user.getRole()))
                .toList();

        PagedResponse<UserResponse> response = new PagedResponse<>(
                userData,
                users.getNumber(),
                users.getSize(),
                users.getTotalElements(),
                users.getTotalPages(),
                users.isLast()
        );

        return ResponseEntity.ok().body(new ApiResponse(MessageConstant.SUCCESSFULLY_FETCHED, true, response));
    }

    @Override
    public ResponseEntity<ApiResponse> getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserResponse response = new UserResponse(user.getId(), user.getEmail(),user.getRole());
            return ResponseEntity.ok().body(new ApiResponse(MessageConstant.SUCCESSFULLY_FETCHED, true, response));
        } else {
            throw new DataNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> updateUserById(Long id, UserResponse userResponse){
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setEmail(userResponse.getEmail());
            userRepository.save(user);
            return ResponseEntity.ok(new ApiResponse("User updated successfully.",true));
        }
        else{
            throw new DataNotFoundException("User not found with id: " + id);
        }

    }

    @Override
    public ResponseEntity<ApiResponse> deleteUserById(Long id) {
        Optional<User> userOptional =  userRepository.findById(id);

        if(userOptional.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("User deleted successfully.",true));
        }
        else{
            throw new DataNotFoundException("User not found with id: " + id);
        }
    }






}

