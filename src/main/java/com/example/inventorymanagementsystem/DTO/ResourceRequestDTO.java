package com.example.inventorymanagementsystem.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResourceRequestDTO {
    private String brand;
    private String model;
    private String specification;

    private LocalDate purchase_date;
    private LocalDate warranty_expiry;

    private Long resource_type_id;
    private Long resource_class_id;
    private Long resource_status_id;
    private Long batch_id; // This is nullable
}
