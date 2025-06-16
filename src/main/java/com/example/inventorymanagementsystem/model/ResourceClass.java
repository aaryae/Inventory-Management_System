package com.example.inventorymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "resource_class")
public class ResourceClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceClassId;

    private String resourceClassName;

    @OneToMany(mappedBy = "resourceClass", cascade = CascadeType.ALL)
    private List<ResourceType> resourceTypes;

}
