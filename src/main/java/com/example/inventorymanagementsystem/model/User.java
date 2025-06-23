package com.example.inventorymanagementsystem.model;


import com.example.inventorymanagementsystem.helper.Role;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "password_last_updated")
    private LocalDateTime passwordLastUpdated;


    @Enumerated(EnumType.STRING)
    private Role role=Role.USER;


    @Column(name = "verification_code")
    private Integer verificationCode=null;

}