package com.example.inventorymanagementsystem.service.impl;

import com.example.inventorymanagementsystem.dtos.response.ApiResponse;
import com.example.inventorymanagementsystem.helper.MessageConstant;
import com.example.inventorymanagementsystem.repository.ResourceRepository;
import com.example.inventorymanagementsystem.repository.ResourceTypeRepository;
import com.example.inventorymanagementsystem.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final ResourceRepository resourceRepository;
    private final ResourceTypeRepository resourceTypeRepository;

    private Map<String, Long> convertToMap(List<Object[]> rawData) {
        Map<String, Long> result = new HashMap<>();
        for (Object[] row : rawData) {
            result.put((String) row[0], (Long) row[1]);
        }
        return result;
    }

    @Override
   public ResponseEntity<ApiResponse> getResourceCountByBrand() {
     Object response=  convertToMap(resourceRepository.countByBrand());
         return ResponseEntity.ok().body(new ApiResponse(MessageConstant.SUCCESSFULLY_FETCHED, true, response));
    }

    @Override
    public ResponseEntity<ApiResponse> getResourceCountByModel(){
        Object response= convertToMap(resourceRepository.countByModel());
        return ResponseEntity.ok().body(new ApiResponse(MessageConstant.SUCCESSFULLY_FETCHED, true, response));
    }

    @Override
    public ResponseEntity<ApiResponse> getResourceCountBySpecification(){
        Object response= convertToMap(resourceRepository.countBySpecification());
        return ResponseEntity.ok().body(new ApiResponse(MessageConstant.SUCCESSFULLY_FETCHED, true, response));
    }

    @Override
    public ResponseEntity<ApiResponse> getResourceCountByResourceTypeName(){
        Object response= convertToMap(resourceTypeRepository.countByResourceType());
        return ResponseEntity.ok().body(new ApiResponse(MessageConstant.SUCCESSFULLY_FETCHED, true, response));
    }


}
