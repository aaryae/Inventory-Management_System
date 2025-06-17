package com.example.inventorymanagementsystem.service.impl;

import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;
import com.example.inventorymanagementsystem.exception.DataNotFoundException;
import com.example.inventorymanagementsystem.model.Resources;
import com.example.inventorymanagementsystem.repository.ResourceRepository;
import com.example.inventorymanagementsystem.service.SearchResourceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@AllArgsConstructor
public class SearchResourceServiceImpl implements SearchResourceService {

    private final ResourceRepository resourceRepository;

    @Override
    public ResponseEntity<ResourceResponseDTO> getResourceByBatchId(long batchId) {
        Resources resource = resourceRepository.findById(batchId)
                .orElseThrow(() -> new DataNotFoundException("Resource not found"));

        ResourceResponseDTO responseDTO = ResourceResponseDTO.builder()
                .resourceId(resource.getResourceId())
                .resourceCode(resource.getResourceCode())
                .brand(resource.getBrand())
                .model(resource.getModel())
                .specification(resource.getSpecification())
                .purchaseDate(resource.getPurchaseDate())
                .warrantyExpiry(resource.getWarrantyExpiry())
                .build();

        return ResponseEntity.ok(responseDTO);
    }

}
