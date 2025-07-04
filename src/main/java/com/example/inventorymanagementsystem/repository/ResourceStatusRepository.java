package com.example.inventorymanagementsystem.repository;

import com.example.inventorymanagementsystem.model.ResourceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResourceStatusRepository extends JpaRepository<ResourceStatus, Long> {
    Optional<ResourceStatus> findByResourceStatusNameIgnoreCase(String name);

}
