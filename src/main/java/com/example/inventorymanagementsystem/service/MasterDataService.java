package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dtos.request.resource.ResourceClassRequestDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceStatusRequestDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceTypeRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface MasterDataService {

    // This is used to fetch all the master data
    ResponseEntity<ApiResponse> getAllResourceTypes();

    ResponseEntity<ApiResponse> getAllResourceClass();

    ResponseEntity<ApiResponse> getAllResourceStatus();



    // This is used for validation and lookup by an ID
    ResponseEntity<ApiResponse> getResourceTypeById(Long resourceId);

    ResponseEntity<ApiResponse> getResourceClassById(Long resourceId);

    ResponseEntity<ApiResponse> getResourceStatusById(Long resourceId);

    // This is used for the creation of resource type, class and status
    ResponseEntity<ApiResponse> createResourceType(ResourceTypeRequestDTO dto);

    ResponseEntity<ApiResponse> createResourceClass(ResourceClassRequestDTO dto);

    ResponseEntity<ApiResponse> createResourceStatus(ResourceStatusRequestDTO dto);
}
