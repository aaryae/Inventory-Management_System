package com.example.inventorymanagementsystem.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResourceUpdateDTO {
    private String model;
    private String brand;
    private String specification;

    private LocalDate purchase_date;
    private LocalDate warranty_expiry;

    private Long resource_status_id;
}
