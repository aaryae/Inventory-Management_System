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
    private Long resource_id;

    private String resourceCode;
    private String brand;
    private String model;
    private String specification;

    private LocalDate purchase_date;
    private LocalDate warranty_expiry;

    private String resource_type;
    private String resource_class;
    private String resource_status;
    private String batchCode;

}
