package com.example.inventorymanagementsystem.service.impl;

import com.example.inventorymanagementsystem.dtos.ResourceUpdateDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;
import com.example.inventorymanagementsystem.helper.MessageConstant;
import com.example.inventorymanagementsystem.model.*;
import com.example.inventorymanagementsystem.repository.BatchRepository;
import com.example.inventorymanagementsystem.repository.ResourceRepository;
import com.example.inventorymanagementsystem.service.MasterDataService;
import com.example.inventorymanagementsystem.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class ResourceServiceImpl implements ResourceService {


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
    public ResponseEntity<ApiResponse> createResources(ResourceRequestDTO request) {
        // Validating master data using MasterDataService
        ResponseEntity<ApiResponse> typeResponse = masterDataService.getResourceTypeById(request.getResourceTypeId());

        if (typeResponse == null || !typeResponse.getStatusCode().is2xxSuccessful() || typeResponse.getBody() == null){
            return ResponseEntity.status(404).body(new ApiResponse("Resource Type Not Found", false));
        }

        ApiResponse apiTypeResponse = typeResponse.getBody();
        Object typeData = apiTypeResponse.getData();

        if (!(typeData instanceof ResourceType resourceType)){
            return ResponseEntity.badRequest().body(new ApiResponse("Invalid Resource Type Format", false));
        }


        ResponseEntity<ApiResponse> classResponse = masterDataService.getResourceClassById(request.getResourceClassId());

        if (classResponse == null || !classResponse.getStatusCode().is2xxSuccessful() || classResponse.getBody() == null){
            return ResponseEntity.status(404).body(new ApiResponse("Resource Class Not Found", false));
        }

        ApiResponse apiClassResponse = classResponse.getBody();
        Object classData = apiClassResponse.getData();

        if (!(classData instanceof ResourceClass resourceClass)){
            return ResponseEntity.badRequest().body(new ApiResponse("Invalid Resource Class Format", false));
        }


        ResponseEntity<ApiResponse> statusResponse = masterDataService.getResourceStatusById(request.getResourceStatusId());

        if (statusResponse == null || !statusResponse.getStatusCode().is2xxSuccessful() || statusResponse.getBody() == null){
            return ResponseEntity.status(404).body(new ApiResponse("Resource Status Not Found", false));
        }

        ApiResponse apiStatusResponse = statusResponse.getBody();
        Object statusData = apiStatusResponse.getData();

        if (!(statusData instanceof ResourceStatus resourceStatus)){
            return ResponseEntity.badRequest().body(new ApiResponse("Invalid Resource Status Format", false));
        }

        // Fetch batch if the batch ID is provided
        Batch batch = null;
        if (request.getBatchId() != null) {
            batch = batchRepository.findById(request.getBatchId())
                    .orElseThrow(() -> new RuntimeException("Batch not found with id: " + request.getBatchId()));

            int currentCount = resourceRepository.countByBatch(batch);

            if (currentCount >= batch.getQuantity()) {
                throw new RuntimeException("Cannot add more resources. Batch of " + batch.getQuantity() + "already reached.");
            }
        }


        // It generates unique resource code
        String resourceCode = generateUniqueResourceCode(resourceType.getResourceTypeName());


        // It creates the resource entity
        Resource resource = new Resource();
        resource.setBrand(request.getBrand());
        resource.setModel(request.getModel());
        resource.setSpecification(request.getSpecification());
        resource.setPurchaseDate(request.getPurchaseDate());
        resource.setWarrantyExpiry(request.getWarrantyExpiry());
        resource.setResourceCode(resourceCode);
        resource.setType(resourceType);
        resource.setResourceClass(resourceClass);
        resource.setResourceStatus(resourceStatus);
        resource.setBatch(batch);


        // It saves the data to Database
        Resource saved = resourceRepository.save(resource);


        // It maps the entity to respond DTO
        return convertToDto(saved);
    }

    @Override
    public List<ResponseEntity<ApiResponse>> createResourcesInBatch(List<ResourceRequestDTO> requestDTOList) {

        List<Resource> resourceToSave = new ArrayList<>();

        for (ResourceRequestDTO dto : requestDTOList) {
            //Validation of master data
            ResponseEntity<ApiResponse> typeResponse = masterDataService.getResourceTypeById(requestDTOList.getFirst().getResourceTypeId());
            if (typeResponse == null || !typeResponse.getStatusCode().is2xxSuccessful() || typeResponse.getBody() == null){
                return Collections.singletonList(ResponseEntity.status(404).body(new ApiResponse("Resource Type Not Found", false)));
            }

            ApiResponse apiTypeResponse = typeResponse.getBody();
            Object typeData = apiTypeResponse.getData();

            if (!(typeData instanceof ResourceType resourceType) ){
                return Collections.singletonList(ResponseEntity.badRequest().body(new ApiResponse("Invalid Resource Type", false)));
            }

            ResponseEntity<ApiResponse> classResponse = masterDataService.getResourceClassById(requestDTOList.getFirst().getResourceClassId());

            if (classResponse == null || !classResponse.getStatusCode().is2xxSuccessful() || classResponse.getBody() == null){
                return Collections.singletonList(ResponseEntity.status(404).body(new ApiResponse("Resource Class Not Found", false)));
            }

            ApiResponse apiClassResponse = classResponse.getBody();
            Object classData = apiClassResponse.getData();

            if (!(classData instanceof ResourceClass resourceClass)){
                return Collections.singletonList(ResponseEntity.badRequest().body(new ApiResponse("Invalid Class Format", false)));
            }

            ResponseEntity<ApiResponse> statusResponse = masterDataService.getResourceStatusById(requestDTOList.getFirst().getResourceStatusId());

            if (statusResponse == null || !statusResponse.getStatusCode().is2xxSuccessful() || statusResponse.getBody() == null){
                return Collections.singletonList(ResponseEntity.status(404).body(new ApiResponse("Resource Status Not Found", false)));
            }

            ApiResponse apiStatusResponse = statusResponse.getBody();
            Object statusData = apiStatusResponse.getData();

            if (!(statusData instanceof ResourceStatus resourceStatus)){
                return Collections.singletonList(ResponseEntity.badRequest().body(new ApiResponse("Invalid Status Format", false)));
            }

            // Fetching the batch
            Batch batch = null;
            if (dto.getBatchId() != null) {
                batch = batchRepository.findById(dto.getBatchId())
                        .orElseThrow(() -> new RuntimeException("Batch not found with id: " + dto.getBatchId()));

                int currentCount = resourceRepository.countByBatch(batch);
                int incomingCount = requestDTOList.size();

                if ((currentCount + incomingCount) > batch.getQuantity()) {
                    throw new RuntimeException("Cannot add " + incomingCount + " resources. Batch capacity of " + batch.getQuantity() +
                            " would be exceeded (Currently " + currentCount + ").");
                }
            }


            // Generating the resource code
            String resourceCode = generateUniqueResourceCode(resourceType.getResourceTypeName());

            // Creating resource entity
            Resource resource = new Resource();
            resource.setBrand(dto.getBrand());
            resource.setModel(dto.getModel());
            resource.setSpecification(dto.getSpecification());
            resource.setPurchaseDate(dto.getPurchaseDate());
            resource.setWarrantyExpiry(dto.getWarrantyExpiry());
            resource.setResourceCode(resourceCode);
            resource.setType(resourceType);
            resource.setResourceClass(resourceClass);
            resource.setResourceStatus(resourceStatus);
            resource.setBatch(batch);

            resourceToSave.add(resource);
        }

        // Saving all in the repository
        List<Resource> savedResources = resourceRepository.saveAll(resourceToSave);

        // Map to the ResponseDTO
        return savedResources.stream().map(this::convertToDto).toList();
    }

    @Override
    public ResponseEntity<ApiResponse> getResourceById(Long resourceId) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found with id" + resourceId));
        return convertToDto(resource);
    }

    @Override
    public List<ResponseEntity<ApiResponse>> getAllResources() {
        List<Resource> resources = resourceRepository.findAll();
        return resources.stream().map(this::convertToDto).toList();
    }

    @Override
    public List<ResponseEntity<ApiResponse>> getResourcesByStatus(Long statusId) {
        // Validates if the status exists
        ResponseEntity<ApiResponse> statusResponse = masterDataService.getResourceStatusById(statusId);

        if (statusResponse == null || !statusResponse.getStatusCode().is2xxSuccessful() || statusResponse.getBody() == null){
            return Collections.singletonList(ResponseEntity.status(404).body(new ApiResponse("Resource Status Not Found", false)));
        }

        ApiResponse apiStatusResponse = statusResponse.getBody();
        Object statusData = apiStatusResponse.getData();

        if (!(statusData instanceof ResourceStatus resourceStatus)){
            return Collections.singletonList(ResponseEntity.badRequest().body(new ApiResponse("Invalid Status Format", false)));

        }
        // Fetch all resources with this status
        List<Resource> resources = resourceRepository.findByResourceStatus(resourceStatus);

        // Converts to response dto
        return resources.stream().map(this::convertToDto).toList();

    }

    @Override
    public ResponseEntity<ApiResponse> updateResource(Long resourceId, ResourceUpdateDTO updateDTO) {
        // Fetches the existing resource
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found with id: " + resourceId));


        // Validation of new status
        ResponseEntity<ApiResponse> statusResponse = masterDataService.getResourceStatusById(updateDTO.getResourceStatusId());

        if (statusResponse == null || !statusResponse.getStatusCode().is2xxSuccessful() || statusResponse.getBody() == null){
            return ResponseEntity.status(404).body(new ApiResponse("Resource Status Not Found", false));
        }

        ApiResponse apiStatusResponse = statusResponse.getBody();
        Object statusData = apiStatusResponse.getData();

        if (!(statusData instanceof ResourceStatus resourceStatus)){
            return ResponseEntity.badRequest().body(new ApiResponse("Invalid Status Type", false));
        }

        resource.setModel(updateDTO.getModel());
        resource.setBrand(updateDTO.getBrand());
        resource.setSpecification(updateDTO.getSpecification());
        resource.setPurchaseDate(updateDTO.getPurchaseDate());
        resource.setWarrantyExpiry(updateDTO.getWarrantyExpiry());
        resource.setResourceStatus(resourceStatus);

        // Save and update the resources
        Resource updated = resourceRepository.save(resource);

        // Mapping to response DTO
        return convertToDto(updated);
    }

    @Override
    public void deleteResource(Long resourceId) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found with id: " + resourceId));
        resourceRepository.delete(resource);
    }

    private static final Random r = new Random();
    public String generateUniqueResourceCode(String typePrefix) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int random = r.nextInt(999);
        return typePrefix.toUpperCase() + "-" + date + "-" + String.format("%03d", random);
    }

    private ResponseEntity<ApiResponse> convertToDto(Resource resource) {
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

        return ResponseEntity.ok().body(new ApiResponse(MessageConstant.SUCCESSFULLY_FETCHED, true, response));
    }


}
