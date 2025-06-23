package com.example.inventorymanagementsystem.controller.batchController;

import com.example.inventorymanagementsystem.dtos.request.BatchRequestDTO;
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
    public ResponseEntity<BatchResponseDTO> createBatch(@RequestBody BatchRequestDTO requestDTO){
        BatchResponseDTO responseDTO = batchService.createBatch(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{batchId}")
    public ResponseEntity<BatchResponseDTO> getBatchById(@PathVariable("batchId") Long batchId){
        BatchResponseDTO responseDTO = batchService.getBatchById(batchId);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<BatchResponseDTO>> getAllBatches(){
        List<BatchResponseDTO> responseDTOList = batchService.getAllBatches();
        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping("/{batchId}/resources")
    public ResponseEntity<List<ResourceResponseDTO>> getResourcesByBatch(@PathVariable Long batchId){
        List<ResourceResponseDTO> responseDTOList = batchService.getResourcesByBatchId(batchId);
        return ResponseEntity.ok(responseDTOList);
    }
}
