package com.example.inventorymanagementsystem.service;

import java.util.Map;

public interface DashboardService {
   public Map<String, Long> getResourceCountByBrand();

    public Map<String, Long> getResourceCountByModel();

    public Map<String, Long> getResourceCountBySpecification();


    public Map<String, Long> getResourceCountByResourceTypeName();
}
