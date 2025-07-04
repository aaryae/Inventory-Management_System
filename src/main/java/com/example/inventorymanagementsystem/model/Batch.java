package com.example.inventorymanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@Table(name = "batch")
@EntityListeners(AuditingEntityListener.class)
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;

    @Column(name = "batchCode", unique = true, nullable = false)
    private String batchCode;

    @CreatedDate
    @Column(name = "createdDate", updatable = false, columnDefinition = "date")
    private LocalDate createdDate;

    @Size(min = 5, max = 50)
    @Column(name = "description", nullable = false, columnDefinition = "text")
    private String description;

    @Column(name = "quantity", nullable = false, columnDefinition = "integer")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resourceTypeId", nullable = false, columnDefinition = "bigint")
    private ResourceType type;
}
