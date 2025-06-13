package com.example.inventorymanagementsystem.dtos.response.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceResponseDTO {
    private Long resourceId;

    private String resourceCode;
    private String brand;
    private String model;
    private String specification;

    private LocalDate purchaseDate;
    private LocalDate warrantyExpiry;

    private String resourceType;
    private String resourceClass;
    private String resourceStatus;
    private String batchCode;

}
