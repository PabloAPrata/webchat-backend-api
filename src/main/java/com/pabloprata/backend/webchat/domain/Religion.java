package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "religions")
public class Religion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "religion_id")
    private Integer religionId;

    @Column(name = "description", unique = true, nullable = false)
    private String description;
}
