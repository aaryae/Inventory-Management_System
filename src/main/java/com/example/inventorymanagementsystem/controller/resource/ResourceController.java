package com.example.inventorymanagementsystem.controller.resource;

import com.example.inventorymanagementsystem.dtos.ResourceUpdateDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceResponseDTO;
import com.example.inventorymanagementsystem.service.impl.ResourceServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resources")
@Tag(name = "Resource APIs", description = "crud for resources")

public class ResourceController {

    private final ResourceServiceImpl resourceService;

    public ResourceController(ResourceServiceImpl resourceService){
        this.resourceService = resourceService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createResource(ResourceRequestDTO requestDTO){
        return resourceService.createResources(requestDTO);
    }

    @PostMapping("/batch")
    public List<ResponseEntity<ApiResponse>> createResourceBatch(@RequestBody List<ResourceRequestDTO> requestDTOList){
        return resourceService.createResourcesInBatch(requestDTOList);
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<ApiResponse> getResourceById(@PathVariable("resourceId") Long resourceId){
        return resourceService.getResourceById(resourceId);
    }

    @GetMapping
    public List<ResponseEntity<ApiResponse>> getAllResources(){
        return resourceService.getAllResources();
    }

    @GetMapping("/status/{statusId}")
    public List<ResponseEntity<ApiResponse>> getByStatusId(@PathVariable("statusId") Long statusId){
        return resourceService.getResourcesByStatus(statusId);
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<ApiResponse> updateResource(@PathVariable("resourceId") Long resourceId, @RequestBody ResourceUpdateDTO resourceUpdate){
        return resourceService.updateResource(resourceId, resourceUpdate);
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> deleteResource(@PathVariable("resourceId") Long resourceId){
        resourceService.deleteResource(resourceId);
        return ResponseEntity.noContent().build();
    }
}
