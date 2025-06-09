package com.example.inventorymanagementsystem.service.impl;

import com.example.inventorymanagementsystem.dtos.ResourceUpdateDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;
import com.example.inventorymanagementsystem.model.*;
import com.example.inventorymanagementsystem.repository.BatchRepository;
import com.example.inventorymanagementsystem.repository.ResourceRepository;
import com.example.inventorymanagementsystem.service.MasterDataService;
import com.example.inventorymanagementsystem.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private MasterDataService masterDataService;

    @Autowired
    private BatchRepository batchRepository;



    @Override
    public ResourceResponseDTO createResources(ResourceRequestDTO request){
        // Validating master data using MasterDataService
        ResourceType type = masterDataService.getResourceTypeById(request.getResource_type_id());
        ResourceClass resourceClass = masterDataService.getResourceClassById(request.getResource_class_id());
        ResourceStatus status = masterDataService.getResourceStatusById(request.getResource_status_id());


        // Fetch batch if the batch ID is provided
        Batch batch = null;
        if (request.getBatch_id() != null){
            batch = batchRepository.findById(request.getBatch_id())
                    .orElseThrow(() -> new RuntimeException("Batch not found with id: " + request.getBatch_id()));
        }


        // It generates unique resource code
        String resourceCode = generateUniqueResourceCode(type.getResource_type_name());


        // It creates the resource entity
        Resources resource = new Resources();
        resource.setBrand(request.getBrand());
        resource.setModel(request.getModel());
        resource.setSpecification(request.getSpecification());
        resource.setPurchaseDate(request.getPurchase_date());
        resource.setWarrantyExpiry(request.getWarranty_expiry());
        resource.setResourceCode(resourceCode);
        resource.setType(type);
        resource.setResourceClass(resourceClass);
        resource.setStatus(status);
        resource.setBatch(batch);


        // It saves the data to Database
        Resources saved = resourceRepository.save(resource);


        // It maps the entity to respond DTO
        ResourceResponseDTO response = new ResourceResponseDTO();
        response.setResource_id(saved.getResource_id());
        response.setResourceCode(saved.getResourceCode());
        response.setBrand(saved.getBrand());
        response.setModel(saved.getModel());
        response.setSpecification(saved.getSpecification());
        response.setPurchase_date(saved.getPurchaseDate());
        response.setWarranty_expiry(saved.getWarrantyExpiry());
        response.setResource_type(saved.getType().getResource_type_name());
        response.setResource_class(saved.getResourceClass().getResource_class_name());
        response.setResource_status(saved.getStatus().getResource_status_name());
        response.setBatchCode(saved.getBatch() != null ? saved.getBatch().getBatchCode() : null);

        return response;
    }



    @Override
    public List<ResourceResponseDTO> createResourcesInBatch(List<ResourceRequestDTO> requestDTOList) {

        List<Resources> resourceToSave = new ArrayList<>();

        for (ResourceRequestDTO dto : requestDTOList){
            //Validation of master data
            ResourceType type = masterDataService.getResourceTypeById(requestDTOList.getFirst().getResource_type_id());
            ResourceClass resourceClass = masterDataService.getResourceClassById(requestDTOList.getFirst().getResource_class_id());
            ResourceStatus status = masterDataService.getResourceStatusById(requestDTOList.getFirst().getResource_status_id());

            // Fetching the batch
            Batch batch = null;
            if (dto.getBatch_id() != null){
                batch= batchRepository.findById(dto.getBatch_id())
                        .orElseThrow(() -> new RuntimeException("Batch not found with id: " + dto.getBatch_id()));
            }

            // Generating the resource code
            String resourceCode = generateUniqueResourceCode(type.getResource_type_name());

            // Creating resource entity
            Resources resource = new Resources();
            resource.setBrand(dto.getBrand());
            resource.setModel(dto.getModel());
            resource.setSpecification(dto.getSpecification());
            resource.setPurchaseDate(dto.getPurchase_date());
            resource.setWarrantyExpiry(dto.getWarranty_expiry());
            resource.setResourceCode(resourceCode);
            resource.setType(type);
            resource.setResourceClass(resourceClass);
            resource.setStatus(status);
            resource.setBatch(batch);

            resourceToSave.add(resource);
        }

        // Saving all in the repository
        List<Resources> savedResources = resourceRepository.saveAll(resourceToSave);

        // Map to the ResponseDTO
        return savedResources.stream().map(saved -> {
            ResourceResponseDTO response = new ResourceResponseDTO();

            response.setResource_id(saved.getResource_id());
            response.setResourceCode(saved.getResourceCode());
            response.setBrand(saved.getBrand());
            response.setModel(saved.getModel());
            response.setSpecification(saved.getSpecification());
            response.setPurchase_date(saved.getPurchaseDate());
            response.setWarranty_expiry(saved.getWarrantyExpiry());
            response.setResource_type(saved.getType().getResource_type_name());
            response.setResource_class(saved.getResourceClass().getResource_class_name());
            response.setResource_status(saved.getStatus().getResource_status_name());
            response.setBatchCode(saved.getBatch() != null ? saved.getBatch().getBatchCode() : null);
            return response;
        }).toList();
    }

    @Override
    public ResourceResponseDTO getResourceById(Long resource_id) {
        Resources resource = resourceRepository.findById(resource_id)
                .orElseThrow(() -> new RuntimeException("Resource not found with id" + resource_id));

        ResourceResponseDTO response = new ResourceResponseDTO();
        response.setResource_id(resource.getResource_id());
        response.setResourceCode(resource.getResourceCode());
        response.setBrand(resource.getBrand());
        response.setModel(resource.getModel());
        response.setSpecification(resource.getSpecification());
        response.setPurchase_date(resource.getPurchaseDate());
        response.setWarranty_expiry(resource.getWarrantyExpiry());
        response.setResource_type(resource.getType().getResource_type_name());
        response.setResource_class(resource.getResourceClass().getResource_class_name());
        response.setResource_status(resource.getStatus().getResource_status_name());
        response.setBatchCode(resource.getBatch() != null ? resource.getBatch().getBatchCode() : null);

        return response;
    }

    @Override
    public List<ResourceResponseDTO> getAllResources() {
        List<Resources> resources = resourceRepository.findAll();

        return resources.stream().map(resource->{

            ResourceResponseDTO response = new ResourceResponseDTO();
            response.setResource_id(resource.getResource_id());
            response.setResourceCode(resource.getResourceCode());
            response.setBrand(resource.getBrand());
            response.setModel(resource.getModel());
            response.setSpecification(resource.getSpecification());
            response.setPurchase_date(resource.getPurchaseDate());
            response.setWarranty_expiry(resource.getWarrantyExpiry());
            response.setResource_type(resource.getType().getResource_type_name());
            response.setResource_class(resource.getResourceClass().getResource_class_name());
            response.setResource_status(resource.getStatus().getResource_status_name());
            response.setBatchCode(resource.getBatch() != null ? resource.getBatch().getBatchCode() : null);

            return response;
        }).toList();
    }

    @Override
    public List<ResourceResponseDTO> getResourcesByStatus(Long status_id) {
        // Validates if the status exists
        ResourceStatus status = masterDataService.getResourceStatusById(status_id);

        // Fetch all resources with this status
        List<Resources> resources = resourceRepository.findByResourceStatus(status);

        // Converts to response dto
        return resources.stream().map(resource -> {
            ResourceResponseDTO response = new ResourceResponseDTO();
            response.setResource_id(resource.getResource_id());
            response.setResourceCode(resource.getResourceCode());
            response.setBrand(resource.getBrand());
            response.setModel(resource.getModel());
            response.setSpecification(resource.getSpecification());
            response.setPurchase_date(resource.getPurchaseDate());
            response.setWarranty_expiry(resource.getWarrantyExpiry());
            response.setResource_type(resource.getType().getResource_type_name());
            response.setResource_class(resource.getResourceClass().getResource_class_name());
            response.setResource_status(resource.getStatus().getResource_status_name());
            response.setBatchCode(resource.getBatch() != null ? resource.getBatch().getBatchCode() : null);

            return response;
        }).toList();

    }

    @Override
    public ResourceResponseDTO updateResource(Long resource_id, ResourceUpdateDTO updateDTO) {
        return null;
    }

    @Override
    public void deleteResource(Long resource_id) {

    }


    public String generateUniqueResourceCode(String typePrefix){
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int random = (int) (Math.random()*1000);
        return typePrefix.toUpperCase() + "-" + date + "-" + String.format("%03d", random);
    }


}
