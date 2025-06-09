package com.example.inventorymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor



public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role;

}
