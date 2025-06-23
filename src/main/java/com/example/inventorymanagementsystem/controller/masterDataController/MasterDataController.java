package com.example.inventorymanagementsystem.controller.masterDataController;

import com.example.inventorymanagementsystem.dtos.request.resource.ResourceClassRequestDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceStatusRequestDTO;
import com.example.inventorymanagementsystem.dtos.request.resource.ResourceTypeRequestDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceClassResponseDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceClassSimpleResponseDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceTypeResponseDTO;
import com.example.inventorymanagementsystem.model.ResourceClass;
import com.example.inventorymanagementsystem.model.ResourceStatus;
import com.example.inventorymanagementsystem.model.ResourceType;
import com.example.inventorymanagementsystem.service.MasterDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/master")
public class MasterDataController {
    private final MasterDataService masterDataService;

    public MasterDataController (MasterDataService masterDataService){
        this.masterDataService = masterDataService;
    }

    @GetMapping("/resource-type")
    public ResponseEntity<List<ResourceTypeResponseDTO>> getAllResourceTypes(){
        List<ResourceTypeResponseDTO> types = masterDataService.getAllResourceTypes();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/resource-type/{resourceId}")
    public ResponseEntity<ResourceType> getResourceTypeById(@PathVariable("resourceId") Long resourceId){
        ResourceType type = masterDataService.getResourceTypeById(resourceId);
        return ResponseEntity.ok(type);
    }

    @GetMapping("/resource-class")
    public ResponseEntity<List<ResourceClassResponseDTO>> getAllResourceClass(){
        List<ResourceClassResponseDTO> classes = masterDataService.getAllResourceClass();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/resource-class/{resourceId}")
    public ResponseEntity<ResourceClass> getResourceClassById(@PathVariable("resourceId") Long resourceId){
        ResourceClass resourceClass = masterDataService.getResourceClassById(resourceId);
        return ResponseEntity.ok(resourceClass);
    }

    @GetMapping("/resource-status")
    public ResponseEntity<List<ResourceStatus>> getAllResourceStatus(){
        List<ResourceStatus> statusList = masterDataService.getAllResourceStatus();
        return ResponseEntity.ok(statusList);
    }

    @GetMapping("/resource-status/{resourceId}")
    public ResponseEntity<ResourceStatus> getResourceStatusById(@PathVariable("resourceId") Long resourceId){
        ResourceStatus status = masterDataService.getResourceStatusById(resourceId);
        return ResponseEntity.ok(status);
    }

    @PostMapping("/resource-type")
    public ResponseEntity<ResourceTypeResponseDTO> createResourceType(ResourceTypeRequestDTO dto){
        ResourceTypeResponseDTO resourceType = masterDataService.createResourceType(dto);
        return ResponseEntity.ok(resourceType);
    }

    @PostMapping("/resource-class")
    public ResponseEntity<ResourceClass> createResourceClass(ResourceClassRequestDTO dto){
        ResourceClass resourceClass = masterDataService.createResourceClass(dto);
        return ResponseEntity.ok(resourceClass);
    }

    @PostMapping("/resource-status")
    public ResponseEntity<ResourceStatus> createResourceStatus(ResourceStatusRequestDTO dto){
        ResourceStatus resourceStatus = masterDataService.createResourceStatus(dto);
        return ResponseEntity.ok(resourceStatus);
    }
}
