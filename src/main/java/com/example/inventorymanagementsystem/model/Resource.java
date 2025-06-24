package com.example.inventorymanagementsystem.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "resources")
public class Resource {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceId;

    @Column(unique = true)
    private String resourceCode;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Size(min = 5, max = 50)
    @Column(name = "specification", columnDefinition = "TEXT")
    private String specification;

    private LocalDate purchaseDate;

    private LocalDate warrantyExpiry;

    // Foreign Keys
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resourceStatusId")
    private ResourceStatus resourceStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resourceTypeId")
    private ResourceType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resourceClassId")
    private ResourceClass resourceClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resourceBatchId", nullable = true)
    private Batch batch;
}
