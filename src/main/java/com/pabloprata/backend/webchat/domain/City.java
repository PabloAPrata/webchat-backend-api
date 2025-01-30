package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id")
    private Integer city_id;

    @NotBlank(message = "Nome da cidade é obrigatório.")
    @Size(max = 100, message = "Nome da cidade pode ter no máximo 100 caracteres.")
    private String name;

    @ManyToOne
    @JoinColumn(name = "fk_state_id", referencedColumnName = "state_id", nullable = false)
    private State state;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
