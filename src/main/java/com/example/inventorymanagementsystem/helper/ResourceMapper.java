package com.example.inventorymanagementsystem.helper;

import com.example.inventorymanagementsystem.dtos.response.resource.ResourceClassResponseDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceClassSimpleResponseDTO;
import com.example.inventorymanagementsystem.dtos.response.resource.ResourceTypeResponseDTO;
import com.example.inventorymanagementsystem.model.ResourceClass;
import com.example.inventorymanagementsystem.model.ResourceType;

import java.util.List;
import java.util.stream.Collectors;

public class ResourceMapper {

    // ✅ ResourceType ➝ DTO with simplified class reference
    public static ResourceTypeResponseDTO toResourceTypeResponseDTO(ResourceType resourceType) {
        if (resourceType == null) return null;

        ResourceTypeResponseDTO dto = new ResourceTypeResponseDTO();
        dto.setResourceTypeId(resourceType.getResourceTypeId());
        dto.setResourceTypeName(resourceType.getResourceTypeName());

        ResourceClassSimpleResponseDTO classDto = new ResourceClassSimpleResponseDTO();
        classDto.setResourceClassId(resourceType.getResourceClass().getResourceClassId());
        classDto.setResourceClassName(resourceType.getResourceClass().getResourceClassName());

        dto.setResourceClass(classDto);

        return dto;
    }

    public static ResourceClassResponseDTO toResourceClassResponseDTO(ResourceClass resourceClass) {
        if (resourceClass == null) return null;

        ResourceClassResponseDTO dto = new ResourceClassResponseDTO();
        dto.setResourceClassId(resourceClass.getResourceClassId());
        dto.setResourceClassName(resourceClass.getResourceClassName());

        List<ResourceTypeResponseDTO> typeDTOs = resourceClass.getResourceTypes()
                .stream()
                .map(type -> {
                    ResourceTypeResponseDTO typeDTO = new ResourceTypeResponseDTO();
                    typeDTO.setResourceTypeId(type.getResourceTypeId());
                    typeDTO.setResourceTypeName(type.getResourceTypeName());
//
//                    ResourceClassSimpleResponseDTO classDto = new ResourceClassSimpleResponseDTO();
//                    classDto.setResourceClassId(resourceClass.getResourceClassId());
//                    classDto.setResourceClassName(resourceClass.getResourceClassName());
                    typeDTO.setResourceClass(null);

                    return typeDTO;
                }).toList();

        dto.setResourceTypes(typeDTOs);
        return dto;
    }

}
