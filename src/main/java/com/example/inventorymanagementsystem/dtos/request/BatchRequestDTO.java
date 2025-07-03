package com.example.inventorymanagementsystem.dtos.request;

public record BatchRequestDTO(
        String resourceTypeName,
        Integer quantity,
        String description
){
}
