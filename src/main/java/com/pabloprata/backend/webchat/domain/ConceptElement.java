package com.pabloprata.backend.webchat.domain;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "concept_elements")
@Data
public class ConceptElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concept_element_id")
    private Integer conceptElementId;

    @ManyToOne
    @JoinColumn(name = "fk_concept_diagram_id", nullable = false)
    private ConceptDiagram conceptDiagram;

    @Column(name = "element_type", nullable = false)
    private String elementType;

    @Column(name = "description", nullable = false)
    private String description;
}
