package com.example.inventorymanagementsystem.exception;

public class BatchLimitExceedException extends RuntimeException {
    public BatchLimitExceedException(String message) {
        super(message);
    }
}
