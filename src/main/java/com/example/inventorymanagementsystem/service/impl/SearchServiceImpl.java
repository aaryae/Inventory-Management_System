package com.example.inventorymanagementsystem.service.impl;

import com.example.inventorymanagementsystem.model.Resources;
import com.example.inventorymanagementsystem.repository.ResourceRepository;
import com.example.inventorymanagementsystem.repository.ResourceSpecifications;
import com.example.inventorymanagementsystem.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final ResourceRepository resourceRepository;

    @Override
    public ResponseEntity<?> filterResources(String brand, String model, LocalDate purchaseDate, String specification){

        Specification<Resources> spec = (root, query, cb) -> cb.conjunction();
        if (brand != null) {
            spec = spec.and(ResourceSpecifications.brandContains(brand));
        }
        if(model != null){
            spec= spec.and(ResourceSpecifications.modelContains(model));
        }
        if (purchaseDate != null) {
        spec = spec.and(ResourceSpecifications.purchasedAfter(purchaseDate));
        }
        if (specification != null) {
            spec = spec.and(ResourceSpecifications.typeEquals(specification));
        }

        return ResponseEntity.ok().body( resourceRepository.findAll(spec));
    }


}







