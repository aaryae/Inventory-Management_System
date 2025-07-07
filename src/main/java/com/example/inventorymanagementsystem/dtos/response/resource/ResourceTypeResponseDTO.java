package com.example.inventorymanagementsystem.dtos.response.resource;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResourceTypeResponseDTO(
        Long resourceTypeId,
        String resourceTypeName,
        ResourceClassSimpleResponseDTO resourceClass
){
}
