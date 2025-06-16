package com.example.inventorymanagementsystem.service.impl;

import com.example.inventorymanagementsystem.repository.ResourceRepository;
import com.example.inventorymanagementsystem.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final ResourceRepository resourceRepository;

    private Map<String, Long> convertToMap(List<Object[]> rawData) {
        Map<String, Long> result = new HashMap<>();
        for (Object[] row : rawData) {
            result.put((String) row[0], (Long) row[1]);
        }
        return result;
    }

    @Override
    public Map<String, Long> getResourceCountByBrand() {
        return convertToMap(resourceRepository.countByBrand());
    }
}
