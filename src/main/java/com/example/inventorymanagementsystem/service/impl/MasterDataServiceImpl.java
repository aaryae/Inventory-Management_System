package com.example.inventorymanagementsystem.service.impl;

import com.example.inventorymanagementsystem.dtos.request.resource.ResourceClassRequestDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceStatusRequestDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceTypeRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceStatusResponseDTO;
import com.example.inventorymanagementsystem.exception.ResourceNotFoundExceptionHandler;
import com.example.inventorymanagementsystem.helper.MessageConstant;
import com.example.inventorymanagementsystem.helper.ResourceMapper;
import com.example.inventorymanagementsystem.model.ResourceClass;
import com.example.inventorymanagementsystem.model.ResourceStatus;
import com.example.inventorymanagementsystem.model.ResourceType;
import com.example.inventorymanagementsystem.repository.ResourceClassRepository;
import com.example.inventorymanagementsystem.repository.ResourceStatusRepository;
import com.example.inventorymanagementsystem.repository.ResourceTypeRepository;
import com.example.inventorymanagementsystem.service.MasterDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MasterDataServiceImpl implements MasterDataService {

    private final ResourceTypeRepository resourceTypeRepository;
    private final ResourceClassRepository resourceClassRepository;
    private final ResourceStatusRepository resourceStatusRepository;

    public MasterDataServiceImpl(ResourceTypeRepository resourceTypeRepository, ResourceClassRepository resourceClassRepository, ResourceStatusRepository resourceStatusRepository){
        this.resourceTypeRepository = resourceTypeRepository;
        this.resourceClassRepository = resourceClassRepository;
        this.resourceStatusRepository = resourceStatusRepository;
    }

    @Override
    public ResponseEntity<ApiResponse> getAllResourceTypes() {
        Object response =  resourceTypeRepository.findAll().stream()
                .map(ResourceMapper::toResourceTypeResponseDTO)
                .toList();
        return ResponseEntity.ok().body(new ApiResponse(MessageConstant.SUCCESSFULLY_FETCHED, true, response));
    }

    @Override
    public ResponseEntity<ApiResponse> getAllResourceClass() {
        Object response =  resourceClassRepository.findAll().stream()
                .map(ResourceMapper::toResourceClassResponseDTO)
                .toList();
        return ResponseEntity.ok().body(new ApiResponse(MessageConstant.SUCCESSFULLY_FETCHED, true, response));
    }

    @Override
    public ResponseEntity<ApiResponse> getAllResourceStatus() {
        Object response = resourceStatusRepository.findAll().stream().map(status -> {
            ResourceStatusResponseDTO statusResponseDTO = new ResourceStatusResponseDTO();
            statusResponseDTO.setResourceStatusId(status.getResourceStatusId());
            statusResponseDTO.setResourceStatusName(status.getResourceStatusName());
            return statusResponseDTO;
        }).toList();
        return ResponseEntity.ok().body(new ApiResponse(MessageConstant.SUCCESSFULLY_FETCHED, true, response));
    }

    @Override
    public ResponseEntity<ApiResponse> getResourceTypeById(Long resourceId) {
        ResourceType resourceType = resourceTypeRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler(MessageConstant.RESOURCE_TYPE, "id", resourceId));
        Object response =  ResourceMapper.toResourceTypeResponseDTO(resourceType);
        return ResponseEntity.ok().body(new ApiResponse(MessageConstant.SUCCESSFULLY_FETCHED, true, response));
    }

    @Override
    public ResponseEntity<ApiResponse> getResourceClassById(Long resourceId) {
        ResourceClass resourceClass =  resourceClassRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler(MessageConstant.RESOURCE_CLASS, "id", resourceId));
        Object response =  ResourceMapper.toResourceClassResponseDTO(resourceClass);
        return ResponseEntity.ok().body(new ApiResponse(MessageConstant.SUCCESSFULLY_FETCHED, true, response));
    }

    @Override
    public ResponseEntity<ApiResponse> getResourceStatusById(Long resourceId) {
        ResourceStatus resourceStatus = resourceStatusRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler(MessageConstant.RESOURCE_STATUS, "id", resourceId));
        ResourceStatusResponseDTO statusResponseDTO = new ResourceStatusResponseDTO();
        statusResponseDTO.setResourceStatusId(resourceStatus.getResourceStatusId());
        statusResponseDTO.setResourceStatusName(resourceStatus.getResourceStatusName());

        return ResponseEntity.ok().body(new ApiResponse(MessageConstant.SUCCESSFULLY_FETCHED, true, statusResponseDTO));
    }

    @Override
    public ResponseEntity<ApiResponse> createResourceType(ResourceTypeRequestDTO dto) {
        ResourceClass resourceClass = resourceClassRepository.findById(dto.getResourceClassId())
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler(MessageConstant.CLASS, "id", dto.getResourceClassId()));


        ResourceType resourceType = new ResourceType();
        resourceType.setResourceTypeName(dto.getResourceTypeName());
        resourceType.setResourceClass(resourceClass);

        ResourceType saved = resourceTypeRepository.save(resourceType);

        Object response =  ResourceMapper.toResourceTypeResponseDTO(saved);
        return ResponseEntity.ok().body(new ApiResponse(MessageConstant.SUCCESSFULLY_FETCHED, true, response));
    }

    @Override
    public ResponseEntity<ApiResponse> createResourceClass(ResourceClassRequestDTO dto) {

        ResourceClass resourceClass = new ResourceClass();
        resourceClass.setResourceClassName(dto.getClassName());
        Object response =  resourceClassRepository.save(resourceClass);
        return ResponseEntity.ok().body(new ApiResponse(MessageConstant.SUCCESSFULLY_FETCHED, true, response));
    }

    @Override
    public ResponseEntity<ApiResponse> createResourceStatus(ResourceStatusRequestDTO dto) {

        ResourceStatus resourceStatus = new ResourceStatus();
        resourceStatus.setResourceStatusName(dto.getStatusName());
        Object response = resourceStatusRepository.save(resourceStatus);
        return ResponseEntity.ok().body(new ApiResponse(MessageConstant.SUCCESSFULLY_FETCHED, true, response));
    }
}
