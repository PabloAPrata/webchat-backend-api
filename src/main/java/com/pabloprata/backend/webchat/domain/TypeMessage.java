package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "type_message")
public class TypeMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 20, unique = true, nullable = false)
    private String name;
}
