package com.example.inventorymanagementsystem.repository;

import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;
import com.example.inventorymanagementsystem.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<ResourceResponseDTO> getResourcesByBatchId(Long batchId);
}
