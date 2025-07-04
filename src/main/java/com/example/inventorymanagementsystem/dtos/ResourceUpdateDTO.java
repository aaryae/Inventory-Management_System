package com.example.inventorymanagementsystem.dtos;

import java.time.LocalDate;

public record ResourceUpdateDTO(
        String brand,
        String model,
        String specification,
        LocalDate purchaseDate,
        LocalDate warrantyExpiry,
        String resourceStatusName
){
}
