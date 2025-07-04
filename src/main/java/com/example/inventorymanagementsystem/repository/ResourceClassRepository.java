package com.example.inventorymanagementsystem.repository;

import com.example.inventorymanagementsystem.model.ResourceClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResourceClassRepository extends JpaRepository<ResourceClass, Long> {
    Optional<ResourceClass> findByResourceClassNameIgnoreCase(String name);
}
