package com.sprintly.sprintly.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "USER_DETAILS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Auto-generated unique ID for each user.

    @Column(nullable = false, unique = true)
    private String emailID;  // Unique constraint ensures no duplicate emails.

    @Column(nullable = false, unique = true)
    private String username;  // Unique username for each user.

    @Column(nullable = false)
    private String password;  // Hashed password (not plain text).
}
