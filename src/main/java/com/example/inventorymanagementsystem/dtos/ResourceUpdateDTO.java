package com.example.inventorymanagementsystem.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResourceUpdateDTO {
    private String brand;
    private String model;
    private String specification;

    private LocalDate purchaseDate;
    private LocalDate warrantyExpiry;

    private String resourceStatusName;
}
