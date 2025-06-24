package com.example.inventorymanagementsystem.controller.auth;

import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import com.example.inventorymanagementsystem.service.DashboardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
@Tag(name="Dashboard APIs" , description = "Apis for dashboard")

public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/count-by-brand")
    public ResponseEntity<ApiResponse> getCountByBrand() {
        return ResponseEntity.ok(dashboardService.getResourceCountByBrand());
    }

    @GetMapping("/count-by-model")
    public ResponseEntity<ApiResponse> getCountByModel(){
        return ResponseEntity.ok(dashboardService.getResourceCountByModel());
    }

    @GetMapping("/count-by-specification")
    public ResponseEntity<ApiResponse> getCountBySpecification(){
        return ResponseEntity.ok(dashboardService.getResourceCountBySpecification());
    }

    @GetMapping("/count-by-resourceType")
    public ResponseEntity<ApiResponse> getCountByResourceTypeName(){
        return ResponseEntity.ok(dashboardService.getResourceCountByResourceTypeName());
    }


}
