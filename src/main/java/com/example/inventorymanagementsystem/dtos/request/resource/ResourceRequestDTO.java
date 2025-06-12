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

    private Long resourceTypeId;
    private Long resourceClassId;
    private Long resourceStatusId;
    private Long batchId; // This is nullable
}
