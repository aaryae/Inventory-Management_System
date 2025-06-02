package com.example.inventorymanagementsystem.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Resources {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int resource_id;

    LocalDate purchase_date;
    LocalDate warranty_expiry;
    int resource_status_id;
    int batch_id;
    int resource_class_id;
    int resource_type_id;
    String brand;
    String model;
    String specification;

}
