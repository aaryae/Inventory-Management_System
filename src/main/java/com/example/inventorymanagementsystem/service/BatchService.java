package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dtos.request.BatchRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.BatchResponseDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;

import java.util.List;

public interface BatchService {

    BatchResponseDTO createBatch(BatchRequestDTO batchRequestDTO);

    BatchResponseDTO getBatchById (Long batch_id);

    List<BatchResponseDTO> getAllBatches();

    List<BatchResponseDTO> getResourcesByBatchId(Long batch_id);

}
