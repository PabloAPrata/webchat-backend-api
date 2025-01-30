package com.pabloprata.backend.webchat.domain;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "medical_history_topics")
public class MedicalHistoryTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "history_topic_id")
    private Integer historyTopicId;

    @ManyToOne
    @JoinColumn(name = "fk_medical_history_id", referencedColumnName = "medical_history_id", nullable = false)
    private MedicalHistory medicalHistory;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "date_register", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateRegister;
}
