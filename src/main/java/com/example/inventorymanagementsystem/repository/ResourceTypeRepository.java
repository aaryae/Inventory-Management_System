package com.example.inventorymanagementsystem.repository;

import com.example.inventorymanagementsystem.model.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceTypeRepository extends JpaRepository<ResourceType, Long> {
}
