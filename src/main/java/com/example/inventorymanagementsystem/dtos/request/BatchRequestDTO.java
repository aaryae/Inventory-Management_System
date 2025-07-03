package com.example.inventorymanagementsystem.dtos.request;

import lombok.Data;

@Data
public class BatchRequestDTO {
    private String resourceTypeName;
    private Integer quantity;
    private String description;
}
