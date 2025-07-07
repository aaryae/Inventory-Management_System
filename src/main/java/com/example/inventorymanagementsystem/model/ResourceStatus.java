package com.example.inventorymanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "resource_status")
public class ResourceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceStatusId;

    @Size(min = 2, max = 12)
    @Column(name = "resourceStatusName", unique = true, nullable = false, columnDefinition = "text")
    private String resourceStatusName;
}
