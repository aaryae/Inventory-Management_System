package com.example.inventorymanagementsystem.model;


import com.example.inventorymanagementsystem.helper.Role;
import com.example.inventorymanagementsystem.helper.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column( nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role=Role.USER;


    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "verification_code")
    private int verificationCode;

}