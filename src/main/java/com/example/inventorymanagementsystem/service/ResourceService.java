package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

}
