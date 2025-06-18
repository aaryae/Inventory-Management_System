package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dtos.request.resource.ResourceClassRequestDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceStatusRequestDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceTypeRequestDTO;
import com.example.inventorymanagementsystem.model.ResourceClass;
import com.example.inventorymanagementsystem.model.ResourceStatus;
import com.example.inventorymanagementsystem.model.ResourceType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MasterDataService {

    // This is used to fetch all the master data
    List<ResourceType> getAllResourceTypes();

    List<ResourceClass> getAllResourceClass();

    List<ResourceStatus> getAllResourceStatus();



    // This is used for validation and lookup by an ID
    ResourceType getResourceTypeById(Long resource_id);

    ResourceClass getResourceClassById(Long resource_id);

    ResourceStatus getResourceStatusById(Long resource_id);

    // This is used for the creation of resource type, class and status
    ResourceType createResourceType(ResourceTypeRequestDTO dto);

    ResourceClass createResourceClass(ResourceClassRequestDTO dto);

    ResourceStatus createResourceStatus(ResourceStatusRequestDTO dto);
}
