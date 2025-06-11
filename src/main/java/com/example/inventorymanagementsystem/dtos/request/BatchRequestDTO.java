package com.example.inventorymanagementsystem.dtos.request;

import lombok.Data;

@Data
public class BatchRequestDTO {
    private Long resourceTypeId;
    private Integer quantity;
    private String description;
}
