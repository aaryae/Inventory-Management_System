package com.example.inventorymanagementsystem.repository;

import com.example.inventorymanagementsystem.model.Resource;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;


//this is called utility class
public  class ResourceSpecifications {

    private ResourceSpecifications(){}


    public static Specification<Resource> brandContains(String brand){
        return (root, query, cb) ->
                brand==null?null : cb.like(cb.lower(root.get("brand")),"%"+brand.toLowerCase()+"%");
    }

    public static Specification<Resource> modelContains(String model){
        return (root, query, cb) ->
                model==null?null : cb.like(cb.lower(root.get("model")),"%"+model.toLowerCase()+"%");
    }

    public static Specification<Resource> typeEquals(String  specification) {
        return (root, query, cb) ->
                specification == null ? null : cb.equal(root.get("specification"), specification);
    }

    public static Specification<Resource> purchasedAfter(LocalDate date) {
        return (root, query, cb) ->
                date == null ? null : cb.greaterThanOrEqualTo(root.get("purchaseDate"), date);
    }

}
