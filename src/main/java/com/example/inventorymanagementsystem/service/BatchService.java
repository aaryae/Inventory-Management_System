package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dtos.request.BatchRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import com.example.inventorymanagementsystem.dtos.response.resource.BatchResponseDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BatchService {

    ResponseEntity<ApiResponse> createBatch(BatchRequestDTO batchRequestDTO);

    ResponseEntity<ApiResponse> getBatchById (Long batchId);

    List<ResponseEntity<ApiResponse>>getAllBatches();

    List<ResponseEntity<ApiResponse>> getResourcesByBatchId(Long batchId);

}
