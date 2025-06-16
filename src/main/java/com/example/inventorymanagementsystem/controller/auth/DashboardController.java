package com.example.inventorymanagementsystem.controller.auth;

import com.example.inventorymanagementsystem.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/count-by-brand")
    public ResponseEntity<Map<String, Long>> getCountByBrand() {
        return ResponseEntity.ok(dashboardService.getResourceCountByBrand());
    }
}
