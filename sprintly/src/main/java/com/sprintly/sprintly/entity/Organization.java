package com.sprintly.sprintly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ORGANIZATION_DETAILS")
@EntityListeners(AuditingEntityListener.class)
public class Organization {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Id
    @NotBlank
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserOrganizationRole> userRoles = new ArrayList<>();

    private String description;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
}
