package com.example.inventorymanagementsystem.service;

import java.util.Map;

public interface DashboardService {
   Map<String, Long> getResourceCountByBrand();

   Map<String, Long> getResourceCountByModel();

    Map<String, Long> getResourceCountBySpecification();

   Map<String, Long> getResourceCountByResourceTypeName();

}
