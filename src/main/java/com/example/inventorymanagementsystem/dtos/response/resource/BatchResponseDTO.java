package com.example.inventorymanagementsystem.dtos.response.resource;

public record BatchResponseDTO(
        Long batchId,
        String batchCode,
        Integer quantity,
        String description,
        String resourceType
){
}
