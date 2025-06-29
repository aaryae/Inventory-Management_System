package com.example.inventorymanagementsystem.controller.batch;

import com.example.inventorymanagementsystem.dtos.request.BatchRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import com.example.inventorymanagementsystem.dtos.response.resource.BatchResponseDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;
import com.example.inventorymanagementsystem.service.impl.BatchServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batches")
@Tag(name = "Batch APIs", description = "APIs for batch")

public class BatchController {

    private final BatchServiceImpl batchService;

    public BatchController(BatchServiceImpl batchService){
        this.batchService = batchService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createBatch(@RequestBody BatchRequestDTO requestDTO){
       return batchService.createBatch(requestDTO);
    }

    @GetMapping("/{batchId}")
    public ResponseEntity<ApiResponse> getBatchById(@PathVariable("batchId") Long batchId){
        return batchService.getBatchById(batchId);
    }

    @GetMapping
    public List<ResponseEntity<ApiResponse>> getAllBatches(){
        return batchService.getAllBatches();
    }

    @GetMapping("/{batchId}/resources")
    public List<ResponseEntity<ApiResponse>> getResourcesByBatch(@PathVariable Long batchId){
        return batchService.getResourcesByBatchId(batchId);
    }
}
