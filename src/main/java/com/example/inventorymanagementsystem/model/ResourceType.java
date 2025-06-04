package com.example.inventorymanagementsystem.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ResourceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resource_type_id;

    private String resource_type_name;

    @ManyToOne
    @JoinColumn(name = "resource_class_id")
    private ResourceClass resourceClass;

}
