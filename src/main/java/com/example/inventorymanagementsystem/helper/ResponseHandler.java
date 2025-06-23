package com.example.inventorymanagementsystem.helper;

import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, boolean success, Object data) {
        ApiResponse response = new ApiResponse(message, success);
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, boolean success) {
        return generateResponse(message, status, success, null);
    }
}
