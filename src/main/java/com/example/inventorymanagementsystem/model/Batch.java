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
public class Batch {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int batch_id;

    LocalDate created_date;
    int resource_type_id;
    int quantity;
}
