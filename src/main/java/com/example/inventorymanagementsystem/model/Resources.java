package com.example.inventorymanagementsystem.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Resources {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resource_id;

    @Column(unique = true)
    private String resourceCode;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    private String specification;

    private LocalDate purchaseDate;

    private LocalDate warrantyExpiry;

    // Foreign Keys
    @ManyToOne
    @JoinColumn(name = "resource_status_id")
    private ResourceStatus status;

    @ManyToOne
    @JoinColumn(name = "resource_type_id")
    private ResourceType type;

    @ManyToOne
    @JoinColumn(name = "resource_class_id")
    private ResourceClass resourceClass;

    @ManyToOne
    @JoinColumn(name = "resource_batch_id", nullable = true)
    private Batch batch;
}
