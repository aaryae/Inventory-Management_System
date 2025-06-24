package com.example.inventorymanagementsystem.controller.auth;


import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import com.example.inventorymanagementsystem.dtos.response.security.UserResponse;
import com.example.inventorymanagementsystem.service.security.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
@Tag(name="Admin-User crud" , description = "crud operations for users ")
public class AdminController {

    private final AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<ApiResponse> getAllUsers(Pageable pageable){
        return adminService.getAllUsers(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse> getUserById( @PathVariable Long id){
        return adminService.getUserById(id);
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<ApiResponse> updateUserById(@PathVariable Long id, @RequestBody UserResponse userResponse){
        return adminService.updateUserById(id, userResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Long id){
        return adminService.deleteUserById(id);
    }

}
