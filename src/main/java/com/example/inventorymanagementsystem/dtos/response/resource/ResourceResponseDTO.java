package com.example.inventorymanagementsystem.dtos.response.resource;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ResourceResponseDTO(
        Long resourceId,
        String resourceCode,
        String brand,
        String model,
        String specification,
        LocalDate purchaseDate,
        LocalDate warrantyExpiry,
        String resourceType,
        String resourceClass,
        String resourceStatus,
        String batchCode
) {
}
