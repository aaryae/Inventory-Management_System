package com.example.inventorymanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class User {

    @Id
    int user_id;
    String username;
    String password_hash;

    boolean account_locked;
    boolean password_expired;

    public enum Role{
        ADMIN, USER;
    }

}
