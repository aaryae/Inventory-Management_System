package com.example.inventorymanagementsystem.dtos.response.resource;

import lombok.Data;

@Data
public class ResourceTypeResponseDTO {
    private Long resourceTypeId;
    private String resourceTypeName;
    private ResourceClassSimpleResponseDTO resourceClass;
}
