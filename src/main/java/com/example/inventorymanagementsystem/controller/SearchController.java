package com.example.inventorymanagementsystem.controller;

import com.example.inventorymanagementsystem.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/filter")
    public ResponseEntity<?> filterResources(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate purchaseDate,
            @RequestParam(required = false) String specification
    ) {
        return searchService.filterResources(brand, model, purchaseDate, specification);
    }
}
