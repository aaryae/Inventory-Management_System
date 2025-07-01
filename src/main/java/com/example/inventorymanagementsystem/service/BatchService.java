package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dtos.request.BatchRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.BatchResponseDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;

import java.util.List;

public interface BatchService {

    BatchResponseDTO createBatch(BatchRequestDTO batchRequestDTO);

    BatchResponseDTO getBatchById (Long batchId);

    List<BatchResponseDTO> getAllBatches();

    List<ResourceResponseDTO> getResourcesByBatchId(Long batchId);

}
