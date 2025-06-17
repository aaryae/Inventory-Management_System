package com.example.inventorymanagementsystem.controller.auth;

import com.example.inventorymanagementsystem.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.support.ManagedProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/count-by-brand")
    public ResponseEntity<Map<String, Long>> getCountByBrand() {
        return ResponseEntity.ok(dashboardService.getResourceCountByBrand());
    }

    @GetMapping("/count-by-model")
    public ResponseEntity<Map<String, Long>> getCountByModel(){
        return ResponseEntity.ok(dashboardService.getResourceCountByModel());
    }

    @GetMapping("/count-by-specification")
    public ResponseEntity<Map<String, Long>> getCountBySpecification(){
        return ResponseEntity.ok(dashboardService.getResourceCountBySpecification());
    }

    @GetMapping("/count-by-resourceType")
    public ResponseEntity<Map<String, Long>> getCountByResourceTypeName(){
        return ResponseEntity.ok(dashboardService.getResourceCountByResourceTypeName());
    }

}
