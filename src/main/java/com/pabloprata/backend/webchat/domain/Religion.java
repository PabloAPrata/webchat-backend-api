package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "religions")
public class Religion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;
}
