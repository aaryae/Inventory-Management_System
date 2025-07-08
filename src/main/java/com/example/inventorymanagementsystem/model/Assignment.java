package com.example.inventorymanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Long assignmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "assigned_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime assignedDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "assigned_by")
    private String assignedBy; //Username of admin who assigned the resources.

    @Column(name = "return_notes")
    private String returnNotes;

    //Constructors for new assignment.
    public Assignment(Resource resource, Employee employee, String assignedBy){
        this.resource = resource;
        this.employee = employee;
        this.assignedBy = assignedBy;
        this.isActive = true;
    }
}
