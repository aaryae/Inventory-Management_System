package com.example.inventorymanagementsystem.repository;

import com.example.inventorymanagementsystem.model.ResourceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceStatusRepository extends JpaRepository<ResourceStatus, Long> {
}
