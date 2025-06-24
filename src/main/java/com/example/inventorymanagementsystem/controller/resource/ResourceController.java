package com.example.inventorymanagementsystem.controller.resource;

import com.example.inventorymanagementsystem.dtos.ResourceUpdateDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceRequestDTO;
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
    public ResponseEntity<ResourceResponseDTO> createResource(ResourceRequestDTO requestDTO){
        ResourceResponseDTO response = resourceService.createResources(requestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<ResourceResponseDTO>> createResourceBatch(@RequestBody List<ResourceRequestDTO> requestDTOList){
        List<ResourceResponseDTO> responseDTOList = resourceService.createResourcesInBatch(requestDTOList);
        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<ResourceResponseDTO> getResourceById(@PathVariable("resourceId") Long resourceId){
        ResourceResponseDTO responseDTO = resourceService.getResourceById(resourceId);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResourceResponseDTO>> getAllResources(){
        List<ResourceResponseDTO> responseDTOList= resourceService.getAllResources();
        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping("/status/{statusId}")
    public ResponseEntity<List<ResourceResponseDTO>> getByStatusId(@PathVariable("statusId") Long statusId){
        List<ResourceResponseDTO> responseDTOList = resourceService.getResourcesByStatus(statusId);
        return ResponseEntity.ok(responseDTOList);
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<ResourceResponseDTO> updateResource(@PathVariable("resourceId") Long resourceId, @RequestBody ResourceUpdateDTO resourceUpdate){
        ResourceResponseDTO responseDTO = resourceService.updateResource(resourceId, resourceUpdate);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> deleteResource(@PathVariable("resourceId") Long resourceId){
        resourceService.deleteResource(resourceId);
        return ResponseEntity.noContent().build();
    }
}
