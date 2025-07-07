package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dtos.ResourceUpdateDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;

import java.util.List;

public interface ResourceService {

    List<ResourceResponseDTO> createResources(List<ResourceRequestDTO> requestDTOList);

    ResourceResponseDTO getResourceById(Long resourceId);

    List<ResourceResponseDTO> getAllResources();

    List<ResourceResponseDTO> getResourcesByStatus(Long statusId);

    ResourceResponseDTO updateResource(Long resourceId, ResourceUpdateDTO updateDTO);

    void deleteResource(Long resourceId);

}
