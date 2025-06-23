package com.example.inventorymanagementsystem.controller.masterDataController;

import com.example.inventorymanagementsystem.model.ResourceClass;
import com.example.inventorymanagementsystem.model.ResourceStatus;
import com.example.inventorymanagementsystem.model.ResourceType;
import com.example.inventorymanagementsystem.service.MasterDataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/master")
@Tag(name = "Master APIs", description = "meta data for resource")

public class MasterDataController {
    private final MasterDataService masterDataService;

    public MasterDataController (MasterDataService masterDataService){
        this.masterDataService = masterDataService;
    }

    @GetMapping("/resource-type")
    public ResponseEntity<List<ResourceType>> getAllResourceTypes(){
        List<ResourceType> types = masterDataService.getAllResourceTypes();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/resource-type/{resourceId}")
    public ResponseEntity<ResourceType> getResourceTypeById(@PathVariable("resourceId") Long resourceId){
        ResourceType type = masterDataService.getResourceTypeById(resourceId);
        return ResponseEntity.ok(type);
    }

    @GetMapping("/resource-class")
    public ResponseEntity<List<ResourceClass>> getAllResourceClass(){
        List<ResourceClass> classes = masterDataService.getAllResourceClass();
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
}
