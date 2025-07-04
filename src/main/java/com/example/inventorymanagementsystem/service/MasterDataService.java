package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dtos.request.resource.ResourceClassRequestDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceStatusRequestDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceTypeRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceClassResponseDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceTypeResponseDTO;
import com.example.inventorymanagementsystem.model.ResourceClass;
import com.example.inventorymanagementsystem.model.ResourceStatus;
import com.example.inventorymanagementsystem.model.ResourceType;

import java.util.List;

public interface MasterDataService {

    // This is used to fetch all the master data
    List<ResourceTypeResponseDTO> getAllResourceTypes();

    List<ResourceClassResponseDTO> getAllResourceClass();

    List<ResourceStatus> getAllResourceStatus();



    // This is used for validation and lookup by an ID
    ResourceType getResourceTypeById(Long resourceId);

    ResourceClass getResourceClassById(Long resourceId);

    ResourceStatus getResourceStatusById(Long resourceId);

    // This is used for the creation of resource type, class and status
    ResourceTypeResponseDTO createResourceType(ResourceTypeRequestDTO dto);

    ResourceClass createResourceClass(ResourceClassRequestDTO dto);

    ResourceStatus createResourceStatus(ResourceStatusRequestDTO dto);


    ResourceType getResourceTypeByName (String name);

    ResourceClass getResourceClassByName (String name);

    ResourceStatus getResourceStatusByName (String name);
}
