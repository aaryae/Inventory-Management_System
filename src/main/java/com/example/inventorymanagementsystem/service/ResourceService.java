package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dtos.ResourceUpdateDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;


import java.util.List;

public interface ResourceService {

    ResourceResponseDTO createResources(ResourceRequestDTO request);

    List<ResourceResponseDTO> createResourcesInBatch(List<ResourceRequestDTO> requestDTOList);

    ResourceResponseDTO getResourceById(Long resourceId);

    List<ResourceResponseDTO> getAllResources();

    List<ResourceResponseDTO> getResourcesByStatus(Long statusId);

    ResourceResponseDTO updateResource(Long resourceId, ResourceUpdateDTO updateDTO);

    void deleteResource(Long resourceId);

}
