package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dtos.request.BatchRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.BatchResponseDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;

import java.util.List;

public interface BatchService {

    BatchResponseDTO createBatch(BatchRequestDTO batchRequestDTO);

    ResourceResponseDTO getBatchById (Long batch_id);

    List<ResourceResponseDTO> getAllBatches();

    List<ResourceResponseDTO> getResourcesByBatchId(Long batch_id);

}
