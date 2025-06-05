package com.example.inventorymanagementsystem.service.implementation;

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
        return List.of();
    }

    @Override
    public ResourceResponseDTO getResourceById(Long resource_id) {
        return null;
    }

    @Override
    public List<ResourceResponseDTO> getAllResources() {
        return List.of();
    }

    @Override
    public List<ResourceResponseDTO> getResourcesByStatus(Long status_id) {
        return List.of();
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
