package com.sprintly.sprintly.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ORGANIZATION_DETAILS")
@EntityListeners(AuditingEntityListener.class)
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    private String description;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
}
