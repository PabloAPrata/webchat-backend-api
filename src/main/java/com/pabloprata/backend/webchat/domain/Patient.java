package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "patients")
public class Patient {

    public Patient(User user, Psychologist psychologist, String patientStatus) {
        this.user = user;
        this.psychologist = psychologist;
        this.patientStatus = patientStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", referencedColumnName = "id", nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_psychologist_id", referencedColumnName = "id", nullable = false)
    private Psychologist psychologist;

    @Column(name = "patient_status", length = 50, nullable = false)
    private String patientStatus;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = java.time.LocalDateTime.now();

}



