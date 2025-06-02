package com.example.inventorymanagementsystem.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ResourceType {


    @Id
    int resource_type_id;

    int resource_type_name;

    int resource_class_id;
}
