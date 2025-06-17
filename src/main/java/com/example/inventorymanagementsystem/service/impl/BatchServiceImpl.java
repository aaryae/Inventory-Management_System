package com.example.inventorymanagementsystem.service.impl;

import com.example.inventorymanagementsystem.dtos.request.BatchRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.BatchResponseDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;
import com.example.inventorymanagementsystem.model.Batch;
import com.example.inventorymanagementsystem.model.ResourceType;
import com.example.inventorymanagementsystem.model.Resources;
import com.example.inventorymanagementsystem.repository.BatchRepository;
import com.example.inventorymanagementsystem.repository.ResourceRepository;
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
    private final ResourceRepository resourceRepository;

    public BatchServiceImpl(BatchRepository batchRepository, MasterDataService masterDataService, ResourceRepository resourceRepository){
        this.batchRepository = batchRepository;
        this.masterDataService = masterDataService;
        this.resourceRepository = resourceRepository;
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
        response.setBatchId(saved.getBatchId());
        response.setBatchCode(saved.getBatchCode());
        response.setQuantity(saved.getQuantity());
        response.setDescription(saved.getDescription());
        response.setResourceType(saved.getType().getResourceTypeName());

        return response;
    }

    @Override
    public BatchResponseDTO getBatchById(Long batchId) {
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found with id: " + batchId));

        BatchResponseDTO responseDTO = new BatchResponseDTO();
        responseDTO.setBatchId(batch.getBatchId());
        responseDTO.setBatchCode(batch.getBatchCode());
        responseDTO.setQuantity(batch.getQuantity());
        responseDTO.setDescription(batch.getDescription());
        responseDTO.setResourceType(batch.getType().getResourceTypeName());

        return responseDTO;
    }

    @Override
    public List<BatchResponseDTO> getAllBatches() {
        List<Batch> batches = batchRepository.findAll();

        return batches.stream().map(batch -> {
            BatchResponseDTO responseDTO = new BatchResponseDTO();
            responseDTO.setBatchId(batch.getBatchId());
            responseDTO.setBatchCode(batch.getBatchCode());
            responseDTO.setQuantity(batch.getQuantity());
            responseDTO.setDescription(batch.getDescription());
            responseDTO.setResourceType(batch.getType().getResourceTypeName());

            return responseDTO;
        }).toList();
    }

    @Override
    public List<ResourceResponseDTO> getResourcesByBatchId(Long batchId) {
        // Validation of batch Id
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new RuntimeException("Batch not found with Id: " + batchId));

        // Fetches resources assigned to the batch
        List<Resources> resources = resourceRepository.findByBatch(batch);

        // Maps to response DTO
        return resources.stream().map(resource -> {
            ResourceResponseDTO responseDTO = new ResourceResponseDTO();
            responseDTO.setResourceId(resource.getResourceId());
            responseDTO.setResourceCode(resource.getResourceCode());
            responseDTO.setBrand(resource.getBrand());
            responseDTO.setModel(resource.getModel());
            responseDTO.setSpecification(resource.getSpecification());
            responseDTO.setPurchaseDate(resource.getPurchaseDate());
            responseDTO.setWarrantyExpiry(resource.getWarrantyExpiry());
            responseDTO.setResourceType(resource.getType().getResourceTypeName());
            responseDTO.setResourceClass(resource.getResourceClass().getResourceClassName());
            responseDTO.setResourceStatus(resource.getResourceStatus().getResourceStatusName());
            responseDTO.setBatchCode(resource.getBatch().getBatchCode());

            return responseDTO;
        }).toList();
    }

    private String generateBatchCode(String typePrefix){
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int random = (int) (Math.random()*1000);
        return typePrefix.toUpperCase() + "-BATCH-" + date + "-" + String.format("%03d", random);
    }
}
