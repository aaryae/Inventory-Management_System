package com.example.inventorymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resource_batch_id;

    private LocalDate createdDate;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "resource_type_id")
    private ResourceType type;
}
