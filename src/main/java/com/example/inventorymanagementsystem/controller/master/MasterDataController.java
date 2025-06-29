package com.example.inventorymanagementsystem.controller.master;

import com.example.inventorymanagementsystem.dtos.request.resource.ResourceClassRequestDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceStatusRequestDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceTypeRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceClassResponseDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceTypeResponseDTO;
import com.example.inventorymanagementsystem.model.ResourceClass;
import com.example.inventorymanagementsystem.model.ResourceStatus;
import com.example.inventorymanagementsystem.model.ResourceType;
import com.example.inventorymanagementsystem.service.MasterDataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/master")
@Tag(name = "Master APIs", description = "meta data for resource")

public class MasterDataController {
    private final MasterDataService masterDataService;

    public MasterDataController (MasterDataService masterDataService){
        this.masterDataService = masterDataService;
    }

    @GetMapping("/resource-type")
    public ResponseEntity<ApiResponse> getAllResourceTypes(){
        return masterDataService.getAllResourceTypes();
    }

    @GetMapping("/resource-type/{resourceId}")
    public ResponseEntity<ApiResponse> getResourceTypeById(@PathVariable("resourceId") Long resourceId){
        return masterDataService.getResourceTypeById(resourceId);
    }

    @GetMapping("/resource-class")
    public ResponseEntity<ApiResponse> getAllResourceClass(){
        return masterDataService.getAllResourceClass();
    }

    @GetMapping("/resource-class/{resourceId}")
    public ResponseEntity<ApiResponse> getResourceClassById(@PathVariable("resourceId") Long resourceId){
        return masterDataService.getResourceClassById(resourceId);
    }

    @GetMapping("/resource-status")
    public ResponseEntity<ApiResponse> getAllResourceStatus(){
        return masterDataService.getAllResourceStatus();
    }

    @GetMapping("/resource-status/{resourceId}")
    public ResponseEntity<ApiResponse> getResourceStatusById(@PathVariable("resourceId") Long resourceId){
        return masterDataService.getResourceStatusById(resourceId);
    }

    @PostMapping("/resource-type")
    public ResponseEntity<ApiResponse> createResourceType(ResourceTypeRequestDTO dto){
        return masterDataService.createResourceType(dto);
    }

    @PostMapping("/resource-class")
    public ResponseEntity<ApiResponse> createResourceClass(ResourceClassRequestDTO dto){
        return masterDataService.createResourceClass(dto);
    }

    @PostMapping("/resource-status")
    public ResponseEntity<ApiResponse> createResourceStatus(ResourceStatusRequestDTO dto){
        return masterDataService.createResourceStatus(dto);
    }
}
