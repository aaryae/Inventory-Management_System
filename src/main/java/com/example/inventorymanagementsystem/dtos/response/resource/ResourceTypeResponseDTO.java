package com.example.inventorymanagementsystem.dtos.response.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResourceTypeResponseDTO {
    private Long resourceTypeId;
    private String resourceTypeName;
    private ResourceClassSimpleResponseDTO resourceClass;
}
