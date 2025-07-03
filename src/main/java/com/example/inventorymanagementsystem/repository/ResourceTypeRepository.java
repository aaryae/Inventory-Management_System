package com.example.inventorymanagementsystem.repository;

import com.example.inventorymanagementsystem.model.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ResourceTypeRepository extends JpaRepository<ResourceType, Long> {
    Optional<ResourceType> findByResourceTypeNameIgnoreCase(String name);


    @Query("SELECT r.resourceTypeName, COUNT(r) FROM ResourceType r GROUP BY r.resourceTypeName ")
    List<Object[]> countByResourceType();
}
