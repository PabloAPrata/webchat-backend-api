package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "gender")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gender_id")
    private Long id;

    @Column(name = "name", length = 1, unique = true, nullable = false)
    private String name;
}
