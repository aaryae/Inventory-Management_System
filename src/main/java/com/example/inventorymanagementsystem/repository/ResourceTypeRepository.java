package com.example.inventorymanagementsystem.repository;

import com.example.inventorymanagementsystem.model.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceTypeRepository extends JpaRepository<ResourceType, Long> {

    @Query("SELECT r.resourceTypeName, COUNT(r) FROM ResourceType r GROUP BY r.resourceTypeName ")
    List<Object[]> countByResourceType();
}
