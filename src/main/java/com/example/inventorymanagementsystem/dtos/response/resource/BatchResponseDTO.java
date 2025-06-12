package com.example.inventorymanagementsystem.dtos.response.resource;

import lombok.Data;

@Data
public class BatchResponseDTO {
    private Long batchId;
    private String batchCode;
    private Integer quantity;
    private String description;
    private String resourceType;
}
