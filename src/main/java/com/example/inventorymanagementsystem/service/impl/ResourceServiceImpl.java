package com.example.inventorymanagementsystem.service.impl;

import com.example.inventorymanagementsystem.dtos.ResourceUpdateDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;
import com.example.inventorymanagementsystem.model.*;
import com.example.inventorymanagementsystem.repository.BatchRepository;
import com.example.inventorymanagementsystem.repository.ResourceRepository;
import com.example.inventorymanagementsystem.repository.ResourceSpecifications;
import com.example.inventorymanagementsystem.service.MasterDataService;
import com.example.inventorymanagementsystem.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public  class ResourceServiceImpl implements ResourceService {

   

    private final ResourceRepository resourceRepository;

    private final MasterDataService masterDataService;
    private final BatchRepository batchRepository;

    @Autowired
    public ResourceServiceImpl(ResourceRepository resourceRepository, MasterDataService masterDataService, BatchRepository batchRepository) {
        this.resourceRepository = resourceRepository;
        this.masterDataService = masterDataService;
        this.batchRepository = batchRepository;
    }

    @Override
    public ResourceResponseDTO createResources(ResourceRequestDTO request){
        // Validating master data using MasterDataService
        ResourceType type = masterDataService.getResourceTypeById(request.getResourceTypeId());
        ResourceClass resourceClass = masterDataService.getResourceClassById(request.getResourceClassId());
        ResourceStatus status = masterDataService.getResourceStatusById(request.getResourceStatusId());


        // Fetch batch if the batch ID is provided
        Batch batch = null;
        if (request.getBatchId() != null){
            batch = batchRepository.findById(request.getBatchId())
                    .orElseThrow(() -> new RuntimeException("Batch not found with id: " + request.getBatchId()));

            int currentCount = resourceRepository.countByBatch(batch);

            if (currentCount >= batch.getQuantity()){
                throw new RuntimeException("Cannot add more resources. Batch of " + batch.getQuantity() + "already reached.");
            }
        }


        // It generates unique resource code
        String resourceCode = generateUniqueResourceCode(type.getResourceTypeName());


        // It creates the resource entity
        Resources resource = new Resources();
        resource.setBrand(request.getBrand());
        resource.setModel(request.getModel());
        resource.setSpecification(request.getSpecification());
        resource.setPurchaseDate(request.getPurchaseDate());
        resource.setWarrantyExpiry(request.getWarrantyExpiry());
        resource.setResourceCode(resourceCode);
        resource.setType(type);
        resource.setResourceClass(resourceClass);
        resource.setResourceStatus(status);
        resource.setBatch(batch);


        // It saves the data to Database
        Resources saved = resourceRepository.save(resource);


        // It maps the entity to respond DTO
        return convertToDto(saved);
    }

    @Override
    public List<ResourceResponseDTO> createResourcesInBatch(List<ResourceRequestDTO> requestDTOList) {

        List<Resources> resourceToSave = new ArrayList<>();

        for (ResourceRequestDTO dto : requestDTOList){
            //Validation of master data
            ResourceType type = masterDataService.getResourceTypeById(requestDTOList.getFirst().getResourceTypeId());
            ResourceClass resourceClass = masterDataService.getResourceClassById(requestDTOList.getFirst().getResourceClassId());
            ResourceStatus status = masterDataService.getResourceStatusById(requestDTOList.getFirst().getResourceStatusId());

            // Fetching the batch
            Batch batch = null;
            if (dto.getBatchId() != null){
                batch= batchRepository.findById(dto.getBatchId())
                        .orElseThrow(() -> new RuntimeException("Batch not found with id: " + dto.getBatchId()));

                int currentCount = resourceRepository.countByBatch(batch);
                int incomingCount = requestDTOList.size();

                if ((currentCount + incomingCount) > batch.getQuantity()){
                    throw new RuntimeException("Cannot add " + incomingCount + " resources. Batch capacity of " + batch.getQuantity() +
                            " would be exceeded (Currently " + currentCount + ").");
                }
            }


            // Generating the resource code
            String resourceCode = generateUniqueResourceCode(type.getResourceTypeName());

            // Creating resource entity
            Resources resource = new Resources();
            resource.setBrand(dto.getBrand());
            resource.setModel(dto.getModel());
            resource.setSpecification(dto.getSpecification());
            resource.setPurchaseDate(dto.getPurchaseDate());
            resource.setWarrantyExpiry(dto.getWarrantyExpiry());
            resource.setResourceCode(resourceCode);
            resource.setType(type);
            resource.setResourceClass(resourceClass);
            resource.setResourceStatus(status);
            resource.setBatch(batch);

            resourceToSave.add(resource);
        }

        // Saving all in the repository
        List<Resources> savedResources = resourceRepository.saveAll(resourceToSave);

        // Map to the ResponseDTO
        return savedResources.stream().map(this::convertToDto).toList();
    }

    @Override
    public ResourceResponseDTO getResourceById(Long resource_id) {
        Resources resource = resourceRepository.findById(resource_id)
                .orElseThrow(() -> new RuntimeException("Resource not found with id" + resource_id));

        return convertToDto(resource);
    }

    @Override
    public List<ResourceResponseDTO> getAllResources() {
        List<Resources> resources = resourceRepository.findAll();

        return resources.stream().map(this::convertToDto).toList();
    }

    @Override
    public List<ResourceResponseDTO> getResourcesByStatus(Long status_id) {
        // Validates if the status exists
        ResourceStatus status = masterDataService.getResourceStatusById(status_id);

        // Fetch all resources with this status
        List<Resources> resources = resourceRepository.findByResourceStatus(status);

        // Converts to response dto
        return resources.stream().map(this::convertToDto).toList();

    }

    @Override
    public ResourceResponseDTO updateResource(Long resource_id, ResourceUpdateDTO updateDTO) {
        // Fetches the existing resource
        Resources resource = resourceRepository.findById(resource_id)
                .orElseThrow(()-> new RuntimeException("Resource not found with id: " + resource_id));


        // Validation of new status
        ResourceStatus status = masterDataService.getResourceStatusById(updateDTO.getResourceStatusId());

        resource.setModel(updateDTO.getModel());
        resource.setBrand(updateDTO.getBrand());
        resource.setSpecification(updateDTO.getSpecification());
        resource.setPurchaseDate(updateDTO.getPurchaseDate());
        resource.setWarrantyExpiry(updateDTO.getWarrantyExpiry());
        resource.setResourceStatus(status);

        // Save and update the resources
        Resources updated = resourceRepository.save(resource);

        // Mapping to response DTO
        return convertToDto(updated);
    }

    @Override
    public void deleteResource(Long resource_id) {
        Resources resource = resourceRepository.findById(resource_id)
                .orElseThrow(() -> new RuntimeException("Resource not found with id: " + resource_id));
        resourceRepository.delete(resource);
    }

    public String generateUniqueResourceCode(String typePrefix){
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int random = (int) (Math.random()*1000);
        return typePrefix.toUpperCase() + "-" + date + "-" + String.format("%03d", random);
    }

    private ResourceResponseDTO convertToDto(Resources resource) {
        ResourceResponseDTO response = new ResourceResponseDTO();
        response.setResourceId(resource.getResourceId());
        response.setResourceCode(resource.getResourceCode());
        response.setBrand(resource.getBrand());
        response.setModel(resource.getModel());
        response.setSpecification(resource.getSpecification());
        response.setPurchaseDate(resource.getPurchaseDate());
        response.setWarrantyExpiry(resource.getWarrantyExpiry());
        response.setResourceType(resource.getType().getResourceTypeName());
        response.setResourceClass(resource.getResourceClass().getResourceClassName());
        response.setResourceStatus(resource.getResourceStatus().getResourceStatusName());
        response.setBatchCode(resource.getBatch() != null ? resource.getBatch().getBatchCode() : null);

        return response;
    }





}
