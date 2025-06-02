package com.example.inventorymanagementsystem.repository;

import com.example.inventorymanagementsystem.model.Resources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resources, Long> {
}
