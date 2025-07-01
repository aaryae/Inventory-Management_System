package com.example.inventorymanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "resource_class")
public class ResourceClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceClassId;

    @Size(min = 2, max = 12)
    @Column(name = "resourceClassName", unique = true, nullable = false, columnDefinition = "text")
    private String resourceClassName;

    @OneToMany(mappedBy = "resourceClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResourceType> resourceTypes;

}
