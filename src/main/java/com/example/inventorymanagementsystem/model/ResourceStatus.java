package com.example.inventorymanagementsystem.model;

import jakarta.persistence.*;
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

    @Column(unique = true)
    private String resourceStatusName;
}
