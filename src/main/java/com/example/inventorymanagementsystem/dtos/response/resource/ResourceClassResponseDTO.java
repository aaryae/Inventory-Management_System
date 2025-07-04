package com.example.inventorymanagementsystem.dtos.response.resource;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
@JsonPropertyOrder({ "resourceClassId", "resourceClassName", "resourceTypes" })
public record ResourceClassResponseDTO(
        Long resourceClassId,
        String resourceClassName,
        List<ResourceTypeResponseDTO> resourceTypes
){
}
