package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;
import org.springframework.http.ResponseEntity;

public interface SearchResourceService {

    public ResponseEntity<ResourceResponseDTO> getResourceByBatchId(long batchId);
}
