package com.example.inventorymanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class ActivityLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int log_id;

    LocalDate timestamp;
     int resource_id;
     int action_type;
     int user_id;
}
