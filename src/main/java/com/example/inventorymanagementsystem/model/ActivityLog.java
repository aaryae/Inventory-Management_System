package com.example.inventorymanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class ActivityLog {


    @Id
    int log_id;

    LocalDate timestamp;
     int resource_id;
     int action_type;
     int user_id;
}
