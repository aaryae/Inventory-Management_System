package com.example.inventorymanagementsystem.exception;

import org.springframework.http.HttpStatus;

public class BusinessLogicException extends RuntimeException{
    public boolean success;
    public Object data;
    public HttpStatus status;

    public BusinessLogicException(String message, boolean success, Object data, HttpStatus status) {
        super(message);
        this.success = success;
        this.data = data;
        this.status = status;
    }
}

