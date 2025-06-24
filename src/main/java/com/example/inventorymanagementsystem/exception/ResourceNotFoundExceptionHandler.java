package com.example.inventorymanagementsystem.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundExceptionHandler extends RuntimeException{

    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    public ResourceNotFoundExceptionHandler(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


}
