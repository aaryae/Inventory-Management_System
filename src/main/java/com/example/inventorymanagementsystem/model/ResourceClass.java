package com.example.inventorymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class ResourceClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resource_class_id;

    private String resource_class_name;

    @OneToMany(mappedBy = "resourceClass", cascade = CascadeType.ALL)
    private List<ResourceType> resourceTypes;

}
