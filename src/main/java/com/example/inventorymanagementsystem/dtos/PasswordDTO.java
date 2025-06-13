package com.example.inventorymanagementsystem.dtos;


import lombok.Data;

@Data
public class PasswordDTO {

    private String oldPassword;
    private String newPassword;
}
