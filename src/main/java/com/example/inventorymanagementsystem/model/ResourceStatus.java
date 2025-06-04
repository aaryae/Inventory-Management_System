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
public class ResourceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resource_status_id;


    public enum Status{
        ACCEPT,REJECT;
    }


}
