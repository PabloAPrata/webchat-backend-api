package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "psychologists")
public class Psychologist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "psychologist_id")
    private Long psychologistId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "crp", nullable = false, unique = true, length = 30)
    private String crp;

    @NotBlank(message = "Senha é obrigatória.")
    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
