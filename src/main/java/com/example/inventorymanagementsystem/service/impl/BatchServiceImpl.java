package com.example.inventorymanagementsystem.service.impl;

import com.example.inventorymanagementsystem.dtos.request.BatchRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.BatchResponseDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;
import com.example.inventorymanagementsystem.model.Batch;
import com.example.inventorymanagementsystem.model.ResourceType;
import com.example.inventorymanagementsystem.repository.BatchRepository;
import com.example.inventorymanagementsystem.service.BatchService;
import com.example.inventorymanagementsystem.service.MasterDataService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;
    private final MasterDataService masterDataService;

    public BatchServiceImpl(BatchRepository batchRepository, MasterDataService masterDataService){
        this.batchRepository = batchRepository;
        this.masterDataService = masterDataService;
    }

    @Override
    public BatchResponseDTO createBatch(BatchRequestDTO batchRequestDTO) {
        // Validate the resource type
        ResourceType resourceType = masterDataService.getResourceTypeById(batchRequestDTO.getResourceTypeId());

        // Generation of batch code
        String batchCode = generateBatchCode(resourceType.getResourceTypeName());

        // Creating the batch entity
        Batch batch = new Batch();
        batch.setBatchCode(batchCode);
        batch.setQuantity(batchRequestDTO.getQuantity());
        batch.setDescription(batchRequestDTO.getDescription());
        batch.setType(resourceType);

        // Saving the batch
        Batch saved =batchRepository.save(batch);

        // Map to the response DTO
        BatchResponseDTO response = new BatchResponseDTO();
        response.setBatchId(saved.getResourceBatchId());
        response.setBatchCode(saved.getBatchCode());
        response.setQuantity(saved.getQuantity());
        response.setDescription(saved.getDescription());
        response.setResourceType(saved.getType().getResourceTypeName());

        return response;
    }

    @Override
    public ResourceResponseDTO getBatchById(Long batch_id) {
        return null;
    }

    @Override
    public List<ResourceResponseDTO> getAllBatches() {
        return List.of();
    }

    @Override
    public List<ResourceResponseDTO> getResourcesByBatchId(Long batch_id) {
        return List.of();
    }

    private String generateBatchCode(String typePrefix){
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int random = (int) (Math.random()*1000);
        return typePrefix.toUpperCase() + "-BATCH-" + date + "-" + String.format("%03d", random);
    }
}
