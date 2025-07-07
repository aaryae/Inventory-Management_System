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

    @Column(name = "resourceTypeName", unique = true, nullable = false, columnDefinition = "text")
    private String resourceTypeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resourceClassId", nullable = false, columnDefinition = "bigint")
    private ResourceClass resourceClass;

}
