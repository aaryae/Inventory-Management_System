package com.example.inventorymanagementsystem.repository;

import com.example.inventorymanagementsystem.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Batch, Long> {
}
