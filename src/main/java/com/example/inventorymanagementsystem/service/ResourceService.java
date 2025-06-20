package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dtos.ResourceUpdateDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;
import com.example.inventorymanagementsystem.model.Resources;


import java.time.LocalDate;
import java.util.List;

public interface ResourceService {

    ResourceResponseDTO createResources(ResourceRequestDTO request);

    List<ResourceResponseDTO> createResourcesInBatch(List<ResourceRequestDTO> requestDTOList);

    ResourceResponseDTO getResourceById(Long resource_id);

    List<ResourceResponseDTO> getAllResources();

    List<ResourceResponseDTO> getResourcesByStatus(Long status_id);

    ResourceResponseDTO updateResource(Long resource_id, ResourceUpdateDTO updateDTO);

    void deleteResource(Long resource_id);

}
