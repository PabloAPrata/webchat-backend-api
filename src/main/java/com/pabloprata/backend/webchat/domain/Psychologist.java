package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "psychologists")
public class Psychologist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "psychologist_id")
    private Integer psychologistId;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id", nullable = false, unique = true)
    private User user;

    @NotBlank(message = "CRP é obrigatório.")
    @Size(max = 30, message = "O CRP pode ter no máximo 30 caracteres.")
    @Column(unique = true, nullable = false)
    private String crp;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
