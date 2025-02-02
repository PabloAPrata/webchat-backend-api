package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "occupations")
public class Occupation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "description", unique = true, nullable = false)
    private String description;
}
