package com.pabloprata.backend.webchat.domain;

import lombok.*;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "concept_diagram")
@Data
public class ConceptDiagram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "core_beliefs")
    private String coreBeliefs;

    @Column(name = "intermediate_beliefs")
    private String intermediateBeliefs;

    @Column(name = "compensatory_strategies")
    private String compensatoryStrategies;

    @Column(name = "date_register", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp dateRegister;
}
