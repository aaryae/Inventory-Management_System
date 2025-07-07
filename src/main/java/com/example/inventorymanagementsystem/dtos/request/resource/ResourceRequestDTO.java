package com.example.inventorymanagementsystem.dtos.request.resource;

import java.time.LocalDate;

public record ResourceRequestDTO(
        String brand,
        String model,
        String specification,
        LocalDate purchaseDate,
        LocalDate warrantyExpiry,
        String resourceTypeName,
        String resourceClassName,
        String resourceStatusName,
        Long batchId
){
}
