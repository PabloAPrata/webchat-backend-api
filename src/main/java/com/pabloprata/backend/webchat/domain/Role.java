package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "roles")
@Data  // Lombok generates getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor  // Lombok generates a no-args constructor
@AllArgsConstructor // Lombok generates a constructor with all fields
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
