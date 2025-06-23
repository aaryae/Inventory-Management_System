package com.example.inventorymanagementsystem.dtos.response.resource;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;
@JsonPropertyOrder({ "resourceClassId", "resourceClassName", "resourceTypes" })
@Data
public class ResourceClassResponseDTO {
    private Long ResourceClassId;
    private String ResourceClassName;
    private List<ResourceTypeResponseDTO> resourceTypes;
}
