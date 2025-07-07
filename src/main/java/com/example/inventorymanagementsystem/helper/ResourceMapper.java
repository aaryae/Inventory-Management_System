package com.example.inventorymanagementsystem.helper;

import com.example.inventorymanagementsystem.dtos.response.resource.ResourceClassResponseDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceClassSimpleResponseDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceTypeResponseDTO;
import com.example.inventorymanagementsystem.model.ResourceClass;
import com.example.inventorymanagementsystem.model.ResourceType;

import java.util.List;

public final class ResourceMapper {

    private ResourceMapper() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static ResourceTypeResponseDTO toResourceTypeResponseDTO(ResourceType resourceType) {
        if (resourceType == null) return null;

        ResourceClassSimpleResponseDTO classDto = new ResourceClassSimpleResponseDTO(
                resourceType.getResourceClass().getResourceClassId(),
                resourceType.getResourceClass().getResourceClassName()
        );

        return new ResourceTypeResponseDTO(
                resourceType.getResourceTypeId(),
                resourceType.getResourceTypeName(),
                classDto
        );
    }

    public static ResourceClassResponseDTO toResourceClassResponseDTO(ResourceClass resourceClass) {
        if (resourceClass == null) return null;

        List<ResourceTypeResponseDTO> typeDTOs = resourceClass.getResourceTypes()
                .stream()
                .map(type -> new ResourceTypeResponseDTO(
                        type.getResourceTypeId(),
                        type.getResourceTypeName(),
                        null // Avoid circular nesting
                )).toList();

        return new ResourceClassResponseDTO(
                resourceClass.getResourceClassId(),
                resourceClass.getResourceClassName(),
                typeDTOs
        );
    }
}
