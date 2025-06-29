package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dtos.ResourceUpdateDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResourceService {

    ResponseEntity<ApiResponse> createResources(ResourceRequestDTO request);

    List<ResponseEntity<ApiResponse>> createResourcesInBatch(List<ResourceRequestDTO> requestDTOList);

    ResponseEntity<ApiResponse> getResourceById(Long resourceId);

    List<ResponseEntity<ApiResponse>> getAllResources();

    List<ResponseEntity<ApiResponse>> getResourcesByStatus(Long statusId);

    ResponseEntity<ApiResponse> updateResource(Long resourceId, ResourceUpdateDTO updateDTO);

    void deleteResource(Long resourceId);

}
