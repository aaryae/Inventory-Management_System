package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dtos.request.resource.ResourceRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;

import java.util.List;

public interface BatchService {

    ResourceResponseDTO createBatch(ResourceRequestDTO batchRequestDTO);

    ResourceResponseDTO getBatchById (Long batch_id);

    List<ResourceResponseDTO> getAllBatches();

    List<ResourceResponseDTO> getResourcesByBatchId(Long batch_id);

}
