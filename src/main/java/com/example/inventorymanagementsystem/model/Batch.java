package com.example.inventorymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resource_batch_id;

    @Column(unique = true)
    private String batchCode;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate createdDate;

    private String Description;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "resource_type_id")
    private ResourceType type;
}
