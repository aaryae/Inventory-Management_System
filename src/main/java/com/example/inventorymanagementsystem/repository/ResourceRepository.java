package com.example.inventorymanagementsystem.repository;

import com.example.inventorymanagementsystem.model.Batch;
import com.example.inventorymanagementsystem.model.ResourceStatus;
import com.example.inventorymanagementsystem.model.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resources, Long> {
    List<Resources> findByResourceStatus(ResourceStatus resourceStatus);

    List<Resources> findByBatch(Batch batch);


    @Query("SELECT r.brand, COUNT(r) FROM Resources r GROUP BY r.brand")
    List<Object[]> countByBrand();


}