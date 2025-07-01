package com.example.inventorymanagementsystem.service.impl;

import com.example.inventorymanagementsystem.dtos.request.resource.ResourceClassRequestDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceStatusRequestDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceTypeRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceClassResponseDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceTypeResponseDTO;
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
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<ResourceTypeResponseDTO> getAllResourceTypes() {
        return resourceTypeRepository.findAll().stream()
                .map(ResourceMapper::toResourceTypeResponseDTO)
                .toList();
    }

    @Override
    public List<ResourceClassResponseDTO> getAllResourceClass() {
        return resourceClassRepository.findAll().stream()
                .map(ResourceMapper::toResourceClassResponseDTO)
                .toList();
    }

    @Override
    public List<ResourceStatus> getAllResourceStatus() {
        return resourceStatusRepository.findAll();
    }

    @Override
    public ResourceType getResourceTypeById(Long resourceId) {
        return resourceTypeRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler(MessageConstant.RESOURCE_TYPE, "id", resourceId));
    }

    @Override
    public ResourceClass getResourceClassById(Long resourceId) {
        return resourceClassRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler(MessageConstant.RESOURCE_CLASS, "id", resourceId));
    }

    @Override
    public ResourceStatus getResourceStatusById(Long resourceId) {
        return resourceStatusRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler(MessageConstant.RESOURCE_STATUS, "id", resourceId));
    }

    @Override
    public ResourceTypeResponseDTO createResourceType(ResourceTypeRequestDTO dto) {
        ResourceClass resourceClass = resourceClassRepository.findById(dto.getResourceClassId())
                .orElseThrow(() -> new ResourceNotFoundExceptionHandler(MessageConstant.CLASS, "id", dto.getResourceClassId()));


        ResourceType resourceType = new ResourceType();
        resourceType.setResourceTypeName(dto.getResourceTypeName());
        resourceType.setResourceClass(resourceClass);

        ResourceType saved = resourceTypeRepository.save(resourceType);

        return ResourceMapper.toResourceTypeResponseDTO(saved);
    }

    @Override
    public ResourceClass createResourceClass(ResourceClassRequestDTO dto) {

        ResourceClass resourceClass = new ResourceClass();
        resourceClass.setResourceClassName(dto.getClassName());
        return resourceClassRepository.save(resourceClass);
    }

    @Override
    public ResourceStatus createResourceStatus(ResourceStatusRequestDTO dto) {

        ResourceStatus resourceStatus = new ResourceStatus();
        resourceStatus.setResourceStatusName(dto.getStatusName());
        return resourceStatusRepository.save(resourceStatus);
    }
}
