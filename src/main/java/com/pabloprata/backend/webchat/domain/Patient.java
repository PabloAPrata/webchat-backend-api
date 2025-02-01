package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patientId;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id", nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_psychologist_id", referencedColumnName = "psychologist_id", nullable = false)
    private Psychologist psychologist;


    @Column(name = "patient_status", length = 50, nullable = false)
    private String patientStatus;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = java.time.LocalDateTime.now();

}



