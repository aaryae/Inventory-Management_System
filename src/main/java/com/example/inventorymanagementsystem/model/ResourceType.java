package com.example.inventorymanagementsystem.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "resource_type")
public class ResourceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceTypeId;

    private String resourceTypeName;

    @ManyToOne
    @JoinColumn(name = "resourceClassId")
    private ResourceClass resourceClass;

}
