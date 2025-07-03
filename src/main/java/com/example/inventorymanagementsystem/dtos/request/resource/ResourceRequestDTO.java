package com.example.inventorymanagementsystem.dtos.request.resource;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResourceRequestDTO {
    private String brand;
    private String model;
    private String specification;

    private LocalDate purchaseDate;
    private LocalDate warrantyExpiry;

    private String resourceTypeName;
    private String resourceClassName;
    private String resourceStatusName;
    private Long batchId; // This is nullable
}
