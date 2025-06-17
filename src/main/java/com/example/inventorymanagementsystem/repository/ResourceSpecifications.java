package com.example.inventorymanagementsystem.repository;

import com.example.inventorymanagementsystem.model.Resources;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ResourceSpecifications {


    public static Specification<Resources> brandContains(String brand){
        return (root, query, cb) ->
                brand==null?null : cb.like(cb.lower(root.get("brand")),"%"+brand.toLowerCase()+"%");
    }

    public static Specification<Resources> modelContains(String model){
        return (root, query, cb) ->
                model==null?null : cb.like(cb.lower(root.get("model")),"%"+model.toLowerCase()+"%");
    }
    public static Specification<Resources> purchasedAfter(LocalDate date) {
        return (root, query, cb) ->
                date == null ? null : cb.greaterThanOrEqualTo(root.get("purchaseDate"), date);
    }

    public static Specification<Resources> typeEquals(Long typeId) {
        return (root, query, cb) ->
                typeId == null ? null : cb.equal(root.get("type").get("id"), typeId);
    }


}
