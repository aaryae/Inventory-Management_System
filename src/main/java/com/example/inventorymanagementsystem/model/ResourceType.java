package com.example.inventorymanagementsystem.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ResourceType {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int resource_type_id;

    int resource_type_name;

    int resource_class_id;
}
